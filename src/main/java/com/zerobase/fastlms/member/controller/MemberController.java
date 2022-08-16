package com.zerobase.fastlms.member.controller;


import com.zerobase.fastlms.member.model.FindIdInput;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;


    //회원가입
    @GetMapping("/member/register")
    public String register() {

        System.out.println("request get!!!");

        return "member/register";
    }


    //회원가입 post
    @PostMapping("/member/register")
    public String registerSubmit(Model model,
                                 HttpServletRequest request, MemberInput parameter) {

        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);


        return "member/register_complete";
    }

    //이메일 인증
    @GetMapping("/member/email-auth")
    public String emilAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";

    }

    //회원정보
    @GetMapping("/member/info")
    public String memberInfo() {

        return "member/info";
    }

    //로그인
    @RequestMapping("/member/login")
    public String memberLogin() {

        return "member/login";
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //비밀번호찾기
    @GetMapping("/member/find/password")
    public String findPassword() {

        return "member/find/password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {
        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/find/password_result";
    }

    @GetMapping("/member/find/password_reset")
    public String resetPassword(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");

        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result", result);

        return "member/find/password_reset";
    }


    // password_reset 에서 form 태그 데이터 받아오기
    @PostMapping("/member/find/password_reset")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter) {

        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(),
                    parameter.getUserPw());
        } catch (Exception e) {
        }

        model.addAttribute("result", result);

        return "member/find/password_reset_result";
    }


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    @GetMapping("/member/find/id")
    public String findId() {
        return "member/find/id";
    }

    public String findIdSubmit(Model model, FindIdInput parameter) {
        boolean result = false;
        try {
            result = memberService.findId(parameter);
        } catch (Exception e) {

        }
        model.addAttribute("result", result);

        return "member/find/id_result";

    }


}
