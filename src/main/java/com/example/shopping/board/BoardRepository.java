package com.example.shopping.board;

import java.util.List;

public interface BoardRepository {
    Board boardsave(Board board);         // 저장
    List<Board> findboard();              // 전체 조회
    Board findById(Long id);              // ID로 조회
    boolean update(Board board);          // 수정
    List<Board> findByWriter(String writer); // 작성자로 조회
}
