package com.example.shopping.member;

import java.util.List;
import java.util.Optional;

// Repository 저장소라는 뜻으로 관용적으로 쓰임
public interface MemberRepository {
   Member save(Member member);
   Optional<Member> findById(String id);

   Optional<Member> login(String id, String pw);
   List<Member> findbyAll();

   Board boardsave(Board board);
   List<Board> findboard(Board board);

   Member updateMember(Member updateMember);
}
