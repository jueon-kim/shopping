package com.example.shopping.member;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberServiceImpl implements MemberRepository {

    private final MemberRepository memberRepository;
    private Member member;
    private Jdbcmember jdbcmember;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
        this.jdbcmember = new Jdbcmember();
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
    @Override
    public Optional<Member> login(String id, String pw) {
        return memberRepository.login(id, pw); // DB 조회
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findbyAll() {
        return memberRepository.findbyAll();
    }

    @Override
    public Member updateMember(Member updateMember) {
        return memberRepository.updateMember(updateMember);
    }

    public boolean existsById(String id) {
        return jdbcmember.existsById(id);
    }

    @Override
    public Board boardsave(Board board) {
        return memberRepository.boardsave(board);
    }

    @Override
    public List<Board> findboard(Board board) {
        return memberRepository.findboard(board);
    }


}
