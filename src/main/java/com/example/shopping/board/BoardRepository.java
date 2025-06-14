package com.example.shopping.board;

import java.util.List;

public interface BoardRepository {
    Board boardsave(Board board);
    List<Board> findboard();
    List<Board> findByMemberId(String memberId); // 작성자 ID가 String인 경우

    boolean update(Board board);
    Board findById(Long id); // 게시글 ID로 조회하는 메소드 추가 (필요에 따라

}
