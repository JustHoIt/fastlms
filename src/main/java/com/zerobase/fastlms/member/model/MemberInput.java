package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
}
