package com.zerobase.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


public interface MemberCode {

    String MEMBER_STATUS_REQ = "REQ";//이용불가(이메일미인증)
    String MEMBER_STATUS_ING = "ING"; //이용가능상태
    String MEMBER_STATUS_STOP = "STOP";  //이용불가상태(정지)
    String MEMBER_STATUS_WITHDRAW = "WITHDRAW";  //이용불가상태(탈퇴)

}
