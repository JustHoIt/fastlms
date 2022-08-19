package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {
    String userId;
    String userPw;
    String userName;
    String userEmail;
    String userPhoneNumber;
    LocalDateTime regDt;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;
    boolean adminYn;

    long totalCount;
    long seq;
    String userStatus;
    LocalDateTime userLoginLog;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .userEmail(member.getUserEmail())
//                .userPw(member.getUserPw())
                .userPhoneNumber(member.getUserPhoneNumber())
                .regDt(member.getRegDt())
                .emailAuthKey(member.getEmailAuthKey())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                .userLoginLog(member.getUserLoginLog()) //로그인 기록 마지막
                .build();
//
    }
}
