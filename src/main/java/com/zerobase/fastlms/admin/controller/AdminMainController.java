package com.zerobase.fastlms.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//관리자페이지 화면 컨트롤러
@Controller
public class AdminMainController {

    @GetMapping("/admin/main.do")
    public String main(){
        return  "admin/main";
    }

}
