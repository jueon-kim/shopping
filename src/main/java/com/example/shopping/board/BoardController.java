package com.example.shopping.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardServiceImple boardServiceImple;

    @Autowired
    public BoardController(BoardServiceImple boardServiceImple) {
        this.boardServiceImple = boardServiceImple;
    }

    @GetMapping("/write")
    public String boardWriteForm() {
        return "board/write"; // boardWrite.html (작성 폼)
    }

    @PostMapping("/write")
    public String boardsave(@ModelAttribute Board board, Model model) {
        Board saveBoard = boardServiceImple.boardsave(board);
        if (saveBoard != null) {
            return "redirect:/board"; // 게시글 목록 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "게시글 저장에 실패했습니다.");
            return "board/write"; // 작성 폼으로 다시 이동
        }
    }

    @GetMapping
    public String boardlist(Model model) {
        model.addAttribute("boards", boardServiceImple.findboard());
        return "board/board"; // boardList.html (게시글 목록)
    }

    @GetMapping("/updateForm/{id}")
    public String boardUpdateForm(@PathVariable Long id, Model model) {
        Board board = boardServiceImple.findById(id);
        if (board != null) {
            model.addAttribute("board", board);
            return "board/boardUpdate"; // boardUpdate.html (수정 폼)
        } else {
            return "redirect:/board"; // 해당 게시글이 없으면 목록으로 리다이렉트
        }
    }

    @PostMapping("/update")
    public String boardUpdate(@ModelAttribute Board board, Model model) {
        Board updatedBoard = boardServiceImple.boardupdate(board);
        if (updatedBoard != null) {
            return "redirect:/board"; // 수정 후 목록 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "게시글 수정에 실패했습니다.");
            model.addAttribute("board", board);
            return "board/boardUpdate"; // 수정 폼으로 다시 이동
        }
    }
}