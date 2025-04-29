package com.example.shopping.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

        @GetMapping("/login")
        public String loginPage() {
            return "login"; // 여기 중요!
        }

    @GetMapping("/join")
    public String joinPage(@RequestParam(value = "join", defaultValue = "이름 미제공") String join, Model model) {
        model.addAttribute("join", join);  // 파라미터가 없으면 기본값 '이름 미제공'을 사용
        System.out.println("Received join param: " + join);  // 파라미터 값 출력

        return "join";  // join.html 템플릿 반환 (앞에 슬래시 제거)
    }
}
