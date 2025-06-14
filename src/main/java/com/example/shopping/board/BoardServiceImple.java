package com.example.shopping.board;

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

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    public boolean updateBoard(Board board) {
        return boardRepository.update(board);
    }

    public List<Board> findByMemberId(String id) {
        return boardRepository.findByMemberId(id);
    }
}