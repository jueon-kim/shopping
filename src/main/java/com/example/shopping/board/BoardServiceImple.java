package com.example.shopping.board;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImple {

    private final BoardRepository boardRepository;

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

    public boolean updateBoard(Board updatedBoard) {
        Board existing = boardRepository.findById(updatedBoard.getId());
        if (existing == null) {
            return false;
        }
        existing.setTitle(updatedBoard.getTitle());
        existing.setContent(updatedBoard.getContent());
        boardRepository.update(existing);  // 혹은 update 메서드, save로도 가능
        return true;
    }

    public List<Board> findByWriter(String writer) {
        return boardRepository.findByWriter(writer);
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}