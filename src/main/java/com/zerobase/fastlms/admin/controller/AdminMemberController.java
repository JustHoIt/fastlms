package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.model.AdminUserInput;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {
    private final MemberService memberService; //멤버 리스트 불러오기


    //회원관리 리스트 불러오기기
    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter) {

        parameter.init();

        List<MemberDto> members = memberService.list(parameter);


        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/member/list";
    }

    //유저 디테일 정보 보내기
    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam parameter) {

        parameter.init();
        MemberDto member = memberService.detail(parameter.getUserId());
        model.addAttribute("member", member);

        return "admin/member/detail";
    }


    //detail 에서 유저 상태 변경
    @PostMapping("/admin/member/status.do")
    public String status(Model model, AdminUserInput parameter) {

        boolean result = memberService.updateStatus(parameter.getUserId(), parameter.getUserStatus());

        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();

    }

    //detail 에서 유저 비밀번호 초기화
    @PostMapping("/admin/member/password.do")
    public String password(Model model, AdminUserInput parameter) {

        boolean result = memberService.updatePassword(parameter.getUserId(), parameter.getUserPw());

        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
}


