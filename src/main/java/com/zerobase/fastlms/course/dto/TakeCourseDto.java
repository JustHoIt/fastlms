package com.zerobase.fastlms.course.dto;

import com.zerobase.fastlms.course.entity.TakeCourse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TakeCourseDto {

    private Long id;
    long courseId;
    String userId;

    long payPrice; //결제금액
    String status; // 상태(수강신청, 결재완료, 수강취소)


    LocalDateTime regDt;//신청일

    String userName;
    String userPhoneNumber;
    String subject;

    long totalCount;
    long seq;

    public static TakeCourseDto of(TakeCourse takeCourse) {

        return TakeCourseDto.builder()
                .id(takeCourse.getId())
                .courseId(takeCourse.getCourseId())
                .userId(takeCourse.getUserId())
                .payPrice(takeCourse.getPayPrice())
                .status(takeCourse.getStatus())
                .regDt(takeCourse.getRegDt())
                .build();
    }


    public String getRegDtText() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return regDt != null ? regDt.format(formatter) : "";
    }

}
