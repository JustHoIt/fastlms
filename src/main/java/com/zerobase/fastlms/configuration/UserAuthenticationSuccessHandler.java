package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.entity.MemberLoginHistory;
import com.zerobase.fastlms.member.repository.MemberLoginRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends
        SimpleUrlAuthenticationSuccessHandler {
    private final MemberRepository memberRepository;
    private final MemberLoginRepository memberLoginRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String userId = authentication.getName();

        memberRepository.findById(userId).ifPresent(e -> {
            e.setUserLoginLog(LocalDateTime.now());
            memberRepository.save(e);
        });


        String userAgent = RequestUtils.getUserAgent(request);
        String userIP = RequestUtils.getUserIP(request);

        memberLoginRepository.save(MemberLoginHistory.builder()
                .userIpAddr(userIP)
                .userAgent(userAgent)
                .userId(userId)
                .logDt(LocalDateTime.now())
                .build());
        super.onAuthenticationSuccess(request, response, authentication);

    }
}
