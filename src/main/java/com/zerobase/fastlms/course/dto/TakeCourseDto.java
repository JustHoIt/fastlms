package com.zerobase.fastlms.course.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class TakeCourseDto {

    private Long id;
    long courseId;
    String userId;

    long payPrice; //결제금액
    String status; // 상태(수강신청, 결재완료, 수강취소)


    LocalDateTime regDt;//신청일

    String userName;
    String phone;
    String subject;

    long totalCount;
    long seq;

}
