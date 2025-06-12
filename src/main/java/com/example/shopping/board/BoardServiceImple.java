package com.example.shopping.board;

import com.example.shopping.member.Jdbcmember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImple {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImple(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board boardsave(Board board) {
        return boardRepository.boardsave(board);
    }

    public List<Board> findboard() {
        return boardRepository.findboard();
    }

    public Board boardupdate(Board board) {
        return boardRepository.boardupdate(board);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }
}