package jh.SimpleBoard.controller;

import jh.SimpleBoard.domain.Member;
import jh.SimpleBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/new")
    public void signUp(Member member) {
        memberService.join(member);
    }
}
