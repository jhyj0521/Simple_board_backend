package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Member;

public interface MemberService {

    void join(Member member);

    int dupMemberId(String memberId);
}
