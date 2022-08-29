package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {
    //adminMode 리스트
    List<CourseDto> list(CourseParam parameter);

    //adminDisplay 강좌등록
    boolean add(CourseInput parameter);

    //adminDisplay 강좌 수정
    boolean set(CourseInput parameter);


    //adminDisplay 강좌 상세정보
    CourseDto getById(long id);

    //adminDisplay 강좌내용삭제
    boolean del(String idList);

    //userDisplay 강좌 목록
    List<CourseDto> frontList(CourseParam parameter);

    //userDisplay 강좌 상세 정보
    CourseDto frontDetail(long id);


    //수강신청 api받아오가
    ServiceResult req(TakeCourseInput parameter);


    //전체강좌 목록록
    List<CourseDto> listAll();
}
