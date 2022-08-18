package com.zerobase.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member implements MemberCode  {
    @Id
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private LocalDateTime regDt;

    private String emailAuthKey;
    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;


    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private boolean adminYn;
    private String userStatus;


    //관리자 여부를 저장할 것인지.
    //회원에 따른 ROLE을 지정할 것인지.



}
