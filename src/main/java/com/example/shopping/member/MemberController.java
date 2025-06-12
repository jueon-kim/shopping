package com.example.shopping.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MemberController {

    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member loginMember = (Member) session.getAttribute("loginMember");
            if (loginMember != null) {
                model.addAttribute("id", loginMember.getId());
            }
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinpage() {
        return "join"; // → templates/join.html 보여줌
    }

//    @GetMapping("/memberUpdate")
//    public String memberUpdate(){
//        return "memberUpdate";
//    }

//    @GetMapping("board")
//    public String board() {
//        return "board";
//    }

    @GetMapping("boardwrite")
    public String boardwriteForm() {
        return "boardwrite";
    }
//    @GetMapping("/members/join")
//    public String joinPage(@RequestParam(value = "join") String join, Model model) {
//        model.addAttribute("join", join);  // 파라미터가 없으면 기본값 '이름 미제공'을 사용
//        System.out.println("Received join param: " + join);  // 파라미터 값 출력
//
//        return "join";  // join.html 템플릿 반환 (앞에 슬래시 제거)
//    }

    @PostMapping("/members/join")
    public String join(@ModelAttribute Member member, Model model) {
        if (memberService.existsById(member.getId())) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "join";
        }

        memberService.save(member);
        return "redirect:/admin/memberlist";
    }

    // 로그인
    @PostMapping("/members/login")
    public String login(@ModelAttribute Member member, Model model,
                        HttpServletRequest request) {
        Optional<Member> result = memberService.login(member.getId(), member.getPw());

        if (result.isPresent()) {
            model.addAttribute("loginMember", result.get());

            //로그인 세션 설정
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30); // 세션 유지 시간

            System.out.println("로그인 성공: " + result.get());
            return "redirect:/index?id=" + member.getId();
        } else {
            System.out.println("로그인 실패: " + member);
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login"; // login.html로 이동
        }
    }
    //로그아웃 세션 종료
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            // 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            // 세션에 회원 정보 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        // 로그인한 회원 정보 DB에서 새로 조회 (필요시)
        Optional<Member> memberInfo = memberService.findById(loginMember.getId());
        model.addAttribute("member", memberInfo);

        if (memberInfo.isPresent()) {
            model.addAttribute("member", memberInfo.get());

            return "mypage";

        }else {
            return "redirect:/login";
        }
    }



    @GetMapping(value = "/admin/memberlist")
    public String memberList(Model model){
        model.addAttribute("members", memberService.findbyAll());
        return "admin/memberlist";
    }


    }
