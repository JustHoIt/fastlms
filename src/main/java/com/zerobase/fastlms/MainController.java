package com.zerobase.fastlms;

import com.zerobase.fastlms.compnets.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// MainPage 클래스를 만드는 목적 =>
// Mapping을 하기 위해서 : 주소와(논리적인 주소 인터넷 주소) 물리적인 파일(프로그래밍) 매핑

// http://www.naver.com/new/list.do
// 하나의 주소에 대해서
// 어디서 매핑? 누가 매핑?
// 후보군? 클래스, 속성, 메소드

// http://localhost:8080/

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index(){

//        String email = "phm3128@naver.com";
//        String subjuect ="안녕하세요. 제로베이스 입니다.";
//        String text = "<p>안녕하세요</p><p>반갑습니다.</p>";
//        mailComponents.sendMail(email, subjuect, text);
//
        return "index";
    }


    // 스프링 -> MVC(View -> 템플릿엔진 화면에 내용을 출력(html)
    // .NET ->  MVC(View -> 출력)
    // python django -> MTV(Template -> 화면출력)
    // 백엔드 과정 -> V(화면에 보여진 부분) -> vmfhsxmdpsem rhkwjd
    // V -> HTML ==>  웹페이지가
    // V -> json ==> API

   //request : WEB -> SERVER
    //response : SERVER -> WEB



}
