package com.zerobase.fastlms.member.controller;


import com.zerobase.fastlms.common.model.ResponseResult;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import com.zerobase.fastlms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiMemberController {
    private final TakeCourseService takeCourseService;


    //회원가입
    @PostMapping("/api/member/course/cancel.api")
    public ResponseEntity<?> cancelCourse(Model model, @RequestBody TakeCourseInput parameter, Principal principal) {
        String userId = principal.getName();
        //내강좌인지 확인 api조작은 쉽기때문에 꼭 비교해야함!
        TakeCourseDto detail = takeCourseService.detail(parameter.getTakeCourseId());
        if (detail == null) {
            ResponseResult result = new ResponseResult(false, "수강 신청 정보가 존재하지 않습니다.");
            return ResponseEntity.ok().body(result);
        }
        if (userId == null || !userId.equals(detail.getUserId())) {
            //내 수강신청 정보가 아니라면
            ResponseResult result = new ResponseResult(false, "수강 신청 정보가 존재하지 않습니다.");
            return ResponseEntity.ok().body(result);
        }

       ServiceResult result = takeCourseService.cancel(parameter.getTakeCourseId());
        if(!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }
        ResponseResult responseResult = new ResponseResult(true, result.getMessage());
        return ResponseEntity.ok().body("");
    }


}
