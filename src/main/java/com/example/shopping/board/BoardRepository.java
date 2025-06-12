package com.example.shopping.board;

import com.example.shopping.member.Member;

import java.util.List;

public interface BoardRepository {
    Board boardsave(Board board);
    List<Board> findboard();
    Board boardupdate(Board board);
    Board findById(Long id); // 게시글 ID로 조회하는 메소드 추가 (필요에 따라


}
