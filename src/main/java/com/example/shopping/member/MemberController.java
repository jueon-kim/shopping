package com.example.shopping.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

        @GetMapping("/login")
        public String loginPage() {
            return "login";
        }

    @GetMapping("/join")
    public String joinPage(@RequestParam(value = "join", defaultValue = "회원가입 진행하기~~") String join, Model model) {
        model.addAttribute("join", join);  // 파라미터가 없으면 기본값 '이름 미제공'을 사용
        System.out.println("Received join param: " + join);  // 파라미터 값 출력

        return "join";  // join.html 템플릿 반환 (앞에 슬래시 제거)
    }

    @GetMapping(value = "/admin/memberlist")
    public String memberList(Model model){
        model.addAttribute("members", memberService.findbyAll());
        return "admin/memberlist";
    }

    @PostMapping("/members/join")
    public String join(@ModelAttribute Member member, Model model) {
        if (memberService.existsById(member.getId())) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "join";
        }

        memberService.save(member);
        return "redirect:/admin/memberlist";
    }

    @PostMapping("/members/login")
    public String login (@ModelAttribute Member member, Model model){
        Optional<Member> result = memberService.login(member.getId(), member.getPw());

        if (result.isPresent()) {
            model.addAttribute("loginMember", result.get());
            return "redirect:index";
        } else {
            model.addAttribute("error", "로그인 실패");
            return "login";
        }
    }


    }
