package com.zerobase.fastlms.member.model;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class FindIdInput {
    private String userName;
    private String userEmail;

}
