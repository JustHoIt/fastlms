package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    //유저화면 강좌
    @GetMapping("/course")
    public String list(Model model, CourseParam parameter) {


        List<CourseDto> list = courseService.frontList(parameter);
        model.addAttribute("list", list);

        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        model.addAttribute("categoryList",categoryList);


        //카테고리 전체 개수 가저오는 영역
        int courseTotalCount = 0;
        if(categoryList != null) {
            for(CategoryDto x : categoryList) {
                courseTotalCount += x.getCourseCount();
            }
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("courseTotalCount", courseTotalCount);


        return "course/index";
    }

    // 유저화면 강좌 상세정보
    @GetMapping("/course/{id}")
    public String courseDetail(Model model, CourseParam parameter) {




       CourseDto detail = courseService.frontDetail(parameter.getId());
       model.addAttribute("detail",detail);


        return "course/detail";
    }

}


