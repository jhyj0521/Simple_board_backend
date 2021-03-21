package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Member;
import jh.SimpleBoard.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public void join(Member member) {
        memberMapper.save(member);
    }

    @Override
    public int dupMemberId(String memberId) {
        return memberMapper.dupMemberId(memberId);
    }

}
