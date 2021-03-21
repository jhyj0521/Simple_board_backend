package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Member;
import jh.SimpleBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/new")
    public BaseResponse signUp(@RequestBody Member member) {
        // 아이디 필수 체크
        if (isEmpty(member.getMemberId())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[] { "아이디" });
        }
        // 사용자 이름 필수 체크
        if (isEmpty(member.getMemberName())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[] { "사용자 이름" });
        }
        // 비밀번호 필수 체크
        if (isEmpty(member.getPassword())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[] { "비밀번호" });
        }

        // 아이디 중복 체크
        int idCnt = memberService.dupMemberId(member.getMemberId());
        if (idCnt > 0) {
            throw new BaseException(BaseResponseCode.CODE_101);
        }

        memberService.join(member);
        return new BaseResponse();
    }
}
