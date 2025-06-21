package com.example.shopping.board;

import com.example.shopping.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceImple boardService;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardServiceImple boardServiceImple, BoardRepository boardRepository) {
        this.boardService = boardServiceImple;
        this.boardRepository = boardRepository;
    }

    @GetMapping("/write")
    public String boardWriteForm() {
        return "board/write"; // boardWrite.html (작성 폼)
    }

    // 게시글 저장
    @PostMapping("/write")
    public String boardsave(@ModelAttribute Board board, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        board.setWriter(String.valueOf(loginMember.getId()));

        Board saved = boardService.boardsave(board);
        if (saved != null) {
            return "redirect:/board";
        } else {
            model.addAttribute("error", "게시글 저장 실패");
            return "board/write";
        }
    }

    @GetMapping
    public String boardlist(Model model) {
        model.addAttribute("boards", boardService.findboard());
        return "board/board"; // boardList.html (게시글 목록)
    }

// 수정 폼 보여주기
@GetMapping("/edit/{id}")
public String editBoardForm(@PathVariable("id") Long id, Model model) {
    Board board = boardService.findById(id);
    if (board != null) {
        model.addAttribute("board", board);
        return "board/edit";  // 수정 폼 뷰
    } else {
        return "redirect:/board";
    }
}



    // 수정 처리 (POST)
    @PostMapping("/edit")
    public String boardUpdate(@ModelAttribute Board board, Model model) {
        boolean success = boardService.updateBoard(board);
        if (success) {
            return "redirect:/board";
        } else {
            model.addAttribute("error", "게시글 수정에 실패했습니다.");
            model.addAttribute("board", board);
            return "board/edit";
        }
    }

    //게시글 삭제
    @PostMapping("/delete/{id}")
    public String deleteboard(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/mypage";

    }
}