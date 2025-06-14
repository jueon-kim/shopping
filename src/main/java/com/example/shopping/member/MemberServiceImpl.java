package com.example.shopping.member;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberServiceImpl implements MemberRepository {

    private final MemberRepository memberRepository;
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
    public boolean update(Member member) {
        return memberRepository.update(member);
    }


}
