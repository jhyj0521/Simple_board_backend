package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Member;
import jh.SimpleBoard.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static jh.SimpleBoard.common.SHA256Util.setEncrypt;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public void join(Member member) {

        // 솔트를 생성하고, 비밀번호를 암호화하여 저장
        setEncrypt(member);

        memberMapper.save(member);
    }

    @Override
    public int dupMemberId(String memberId) {
        return memberMapper.dupMemberId(memberId);
    }

}
