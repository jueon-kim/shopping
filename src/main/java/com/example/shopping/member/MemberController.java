package com.example.shopping.member;

import com.example.shopping.board.Board;
import com.example.shopping.board.BoardServiceImple;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final BoardServiceImple boardService;

    public MemberController(MemberServiceImpl memberService, BoardServiceImple boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member loginMember = (Member) session.getAttribute("loginMember");
            System.out.println("index 세션 로그인 회원: " + loginMember);
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


    @GetMapping("boardwrite")
    public String boardwriteForm() {
        return "boardwrite";
    }

    @PostMapping("/members/join")
    public String join(@ModelAttribute Member member, Model model) {
        Optional<Member> existingMember = memberService.findById(member.getId());

        if (existingMember.isPresent()) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "join";
        }

        memberService.save(member);
        return "redirect:/admin/memberlist";
    }


    // 로그인
    @PostMapping("/members/login")
    public String login(@ModelAttribute Member member, Model model, HttpServletRequest request) {
        Optional<Member> result = memberService.login(member.getId(), member.getPw());

        if (result.isPresent()) {
            Member loginMember = result.get();
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", loginMember);

            System.out.println("로그인 성공: " + loginMember);
            return "redirect:/index";
        } else {
            System.out.println("로그인 실패");
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login";
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
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        model.addAttribute("member", loginMember);

        // ✅ 게시글 조회
        List<Board> boards = boardService.findByMemberId(loginMember.getId());
        model.addAttribute("boards", boards);

        return "mypage";
    }





    @GetMapping(value = "/admin/memberlist")
    public String memberList(Model model){
        model.addAttribute("members", memberService.findbyAll());
        return "admin/memberlist";
    }


    }
