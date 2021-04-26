package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Member;

public interface MemberService {

    void join(Member member);

    int dupMemberId(String memberId);

    String createToken(Member member);

    int idPassCheck(Member member);

    String getMemberName(String memberId);
}
