package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.TakeCourseParam;

import java.util.List;
//수강관리와 관련된 리스트
public interface TakeCourseService {

    //Admin Display(User 가 신청한 수강)
    List<TakeCourseDto> list(TakeCourseParam parameter);
}
