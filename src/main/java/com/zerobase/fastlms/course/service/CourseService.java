package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;

import java.util.List;

public interface CourseService {
    //리스트
    List<CourseDto> list(CourseParam parameter);

    //강좌등록
    boolean add(CourseInput parameter);


    //강좌 상세정보
    CourseDto getById(long id);
}
