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

    @PostMapping("/write")
    public String boardsave(@ModelAttribute Board board, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/login";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        board.setMember_id(loginMember.getId());  // ✅ 작성자 정보 저장

        Board saveBoard = boardService.boardsave(board);
        if (saveBoard != null) {
            return "redirect:/board";
        } else {
            model.addAttribute("error", "게시글 저장에 실패했습니다.");
            return "board/write";
        }
    }



    @GetMapping
    public String boardlist(Model model) {
        model.addAttribute("boards", boardService.findboard());
        return "board/board"; // boardList.html (게시글 목록)
    }

//    @GetMapping("/board/update/{id}")
//    public String boardUpdateForm(@PathVariable Long id, Model model) {
//        Board board = boardService.findById(id);
//        if (board != null) {
//            model.addAttribute("board", board);
//            return "board/boardUpdate"; // boardUpdate.html (수정 폼)
//        } else {
//            return "redirect:/board"; // 해당 게시글이 없으면 목록으로 리다이렉트
//        }
//    }
    @GetMapping("/edit/{id}")
    public String editBoardForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        if (board != null) {
            model.addAttribute("board", board);
            return "board/edit";
        } else {
            return "redirect:/board";
        }
    }

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

    @PostMapping("/board/edit/{id}")
    public String editBoard(@PathVariable("id") Long id, @ModelAttribute Board updatedBoard) {
        boardService.updateBoard(updatedBoard); // 서비스에서 DB 업데이트
        return "redirect:/board"; // 수정 후 게시판 목록 등으로 이동
    }

    public void updateBoard(Long id, Board updatedBoard) {
        Optional<Board> optional = Optional.ofNullable(boardRepository.findById(id));
        if (optional.isPresent()) {
            Board board = optional.get();
            board.setTitle(updatedBoard.getTitle());
            board.setContent(updatedBoard.getContent());
            boardRepository.boardsave(board); // DB 반영
        }
    }
}