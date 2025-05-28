package com.example.shopping.member;

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
    public String index(String id, Model model) {
        model.addAttribute("id", id);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/admin/memberlist")
    public String memberList(Model model){
        model.addAttribute("members", memberService.findbyAll());
        return "admin/memberlist";
    }

    @GetMapping("/join")
    public String joinpage() {
        return "join"; // → templates/join.html 보여줌
    }

    @GetMapping("/memberUpdate")
    public String memberUpdate(){
        return "memberUpdate";
    }

    @GetMapping("board")
    public String board() {
        return "board";
    }

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
    @PostMapping("/members/login")
    public String login(@ModelAttribute Member member, Model model) {
        Optional<Member> result = memberService.login(member.getId(), member.getPw());

        if (result.isPresent()) {
            model.addAttribute("loginMember", result.get());
            System.out.println("로그인 성공: " + result.get());
            return "redirect:/index?id=" + member.getId();
        } else {
            System.out.println("로그인 실패: " + member);
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login"; // login.html로 이동
        }
    }

    @PostMapping("/boardwrite")
    public String boardsave(@ModelAttribute Board board, Model model){
        Board saveBoard = memberService.boardsave(board);

        if(saveBoard != null){

            return "redirect:/board";
        }else {
            model.addAttribute("error", "게시글 저장에 실패 했습니다");

            return "redirect:boardwrite";
        }
    }



    }
