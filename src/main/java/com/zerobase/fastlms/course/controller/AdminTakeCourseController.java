package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import com.zerobase.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    //강좌
    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, CourseParam parameter) {

        parameter.init();

        List<CourseDto> courseDtos = courseService.list(parameter);


        long totalCount = 0;

        if (!CollectionUtils.isEmpty(courseDtos)) {
            totalCount = courseDtos.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("list", courseDtos);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/takecourse/list";
    }




}


