package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.common.JwtTokenUtil;
import jh.SimpleBoard.domain.Member;
import jh.SimpleBoard.mapper.MemberMapper;
import jh.SimpleBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jh.SimpleBoard.common.SHA256Util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void join(Member member) {
        // 난수 솔트를 생성
        String salt = generateSalt();
        member.setSalt(salt);

        // 비밀번호를 암호화
        String password = getEncrypt(member.getPassword(), salt);
        member.setPassword(password);

        memberMapper.save(member);
    }

    @Override
    public String createToken(Member member) {
        // 회원 아이디로 회원 번호와 이름을 받아옴
        String memberNo = memberMapper.getMemberNo(member.getMemberId());
        String memberName = memberMapper.getMemberName(member.getMemberId());

        String token = jwtTokenUtil.createToken(memberNo, memberName);
        return token;
    }

    @Override
    public int dupMemberId(String memberId) {
        return memberMapper.findById(memberId);
    }

    @Override
    public int idPassCheck(Member member) {
        // 회원이 존재하는지 확인
        int memberCnt = memberMapper.findById(member.getMemberId());
        if (memberCnt == 0) {
            return memberCnt;
        }

        // 회원의 솔트를 가져와서 암호화 한 뒤, 암호화 한 비밀번호를 설정
        String salt = memberMapper.getMemberSalt(member.getMemberId());
        String password = getEncrypt(member.getPassword(), salt);
        member.setPassword(password);

        return memberMapper.idPassCheck(member);
    }

    @Override
    public String getMemberName(String memberId) {
        return memberMapper.getMemberName(memberId);
    }

}
