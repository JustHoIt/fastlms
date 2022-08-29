package com.zerobase.fastlms.member.controller;


import com.zerobase.fastlms.member.dto.MemberDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.service.TakeCourseService;
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
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final TakeCourseService takeCourseService;




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
    public String memberInfo(Model model, Principal principal) {


        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberSubmit(Model model, MemberInput parameter, Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);
        ServiceResult result = memberService.updateMemberInfo(parameter);
        if(!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/info-password-update")
    public String memberPassword(Model model, Principal principal) {


        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail",detail);

        return "member/info-password-update";
    }

    @PostMapping("/member/info-password-update")
    public String memberPasswordSubmit(Model model
            , MemberInput parameter
            , Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result = memberService.updateMemberPassword(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";

    }

    @GetMapping("/member/info-takeCourse-list")
    public String memberTakeCourse(Model model, Principal principal) {


        String userId = principal.getName();
        List<TakeCourseDto> list = takeCourseService.myCourse(userId);

        model.addAttribute("list", list);



        return "member/info-takeCourse-list";
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
        System.out.println(parameter);
        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(),
                    parameter.getPassword());
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



    //회원탈퇴 페이지
    @GetMapping("/member/withdraw")
    public String memberWithdraw(Model model) {


        return "member/withdraw";
    }

    @PostMapping("/member/withdraw")
    public String memberWithdrawSubmit(Model model, MemberInput parameter, Principal principal) {

        String userId = principal.getName();

        ServiceResult result = memberService.withdraw(userId, parameter.getUserPw());
        if(!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/logout";
    }


}
