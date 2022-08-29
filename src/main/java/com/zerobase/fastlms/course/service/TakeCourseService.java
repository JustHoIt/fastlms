package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;

import java.util.List;
//수강관리와 관련된 리스트
public interface TakeCourseService {

    //Admin Display(User 가 신청한 수강)
    List<TakeCourseDto> list(TakeCourseParam parameter);

    //수강 상세정보
    TakeCourseDto detail(long id);


    //수강내용 상태 변경경
   ServiceResult updateStatus(long id, String status);


   //UserDisplay 수강 목록
   List<TakeCourseDto> myCourse(String userId);


   //UserDisplay수강신청 취소
   ServiceResult cancel(long id);
}
