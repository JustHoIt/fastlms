package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.compnets.MailComponents;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.excepion.MemberNotEmailAuthException;
import com.zerobase.fastlms.member.model.FindIdInput;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if (optionalMember.isPresent()) {
            //데이터베이스에 이미 입력한 userId의 값이 존재
            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getUserPw(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userPw(encPassword)
                .userName(parameter.getUserName())
                .userEmail(parameter.getUserEmail())
                .userPhoneNumber(parameter.getUserPhoneNumber())
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();
        memberRepository.save(member);


        String email = parameter.getUserEmail();
        String subject = "fastlms 사이트 가입을 축하드립니다.";
        String text = "<p>fastlms 사이트 가입을 축하드립니다.</p> <p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if (!optionalMember.isPresent()) {
            return false;
        }
        Member member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);
        return true;
    }

    //재설정 이메일 보내기
    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {
        Optional<Member> optionalMember = memberRepository
                .findByUserIdAndUserEmailAndUserName(parameter.getUserId()
                        , parameter.getUserEmail(), parameter.getUserName());
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");

        }

        Member member = optionalMember.get();

        String uuid = UUID.randomUUID().toString();
        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusHours(1)); //1시간동안만 유효
        memberRepository.save(member);

        String email = parameter.getUserEmail();
        String subject = "[fastlms] 비밀번호 재설정 메일입니다.";
        String text = "<p>fastlms 비밀번호 재설정 메일입니다.</p> <p>아래 링크를 클릭하셔서 비밀번호 재설정을 해주세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/member/find/password_reset?id=" + uuid + "'> 비밀번호 재설정 링크 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }



    @Override
    public boolean resetPassword(String uuid, String password) {
        System.out.println("resetPassword 실행됨");
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);


        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if(member.getResetPasswordLimitDt() == null) {
            throw  new RuntimeException("유효한 날짜가 아닙니다.");
        }

        if(member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw  new RuntimeException("유효한 날짜가 아닙니다.");
        }


        System.out.println("resetPassword 저장준비");
        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setUserPw(encPassword);
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        memberRepository.save(member);

        return true;
    }


    //재설정 비밀번호 사이트 유효검사
    @Override
    public boolean checkResetPassword(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        if(member.getResetPasswordLimitDt() == null) {
            throw  new RuntimeException("유효한 날짜가 아닙니다.");
        }
        if(member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw  new RuntimeException("유효한 날짜가 아닙니다.");
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(username);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        if (!member.isEmailAuthYn()) {
            throw new MemberNotEmailAuthException("가입시 입력한 이메일로 발송된 인증을 활성화 해주세요.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(member.getUserId(), member.getUserPw(), grantedAuthorities) {
        };
    }

    @Override
    public boolean findId(FindIdInput parameter) {
        Optional<Member> optionalMember = memberRepository
                .findByUserNameAndUserEmail(parameter.getUserName(), parameter.getUserEmail());
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }
        return true;
    }
}
