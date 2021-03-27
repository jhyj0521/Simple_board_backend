package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Member;
import jh.SimpleBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param member
     * @return
     */
    @PostMapping("/new")
    public BaseResponse signUp(@RequestBody Member member) {
        // 아이디 필수 체크
        if (isEmpty(member.getMemberId())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"아이디"});
        }
        // 사용자 이름 필수 체크
        if (isEmpty(member.getMemberName())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"사용자 이름"});
        }
        // 비밀번호 필수 체크
        if (isEmpty(member.getPassword())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"비밀번호"});
        }

        // 아이디 중복 체크
        int idCnt = memberService.dupMemberId(member.getMemberId());
        if (idCnt > 0) {
            throw new BaseException(BaseResponseCode.CODE_101);
        }

        memberService.join(member);
        return new BaseResponse();
    }

    /**
     * 로그인
     * @param member
     * @param response
     * @return
     */
    @PostMapping("/login")
    public BaseResponse signIn(@RequestBody Member member, HttpServletResponse response) {
        // 아이디 필수 체크
        if (isEmpty(member.getMemberId())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"아이디"});
        }
        // 비밀번호 필수 체크
        if (isEmpty(member.getPassword())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"비밀번호"});
        }

        // 아이디, 비밀번호 일치 여부
        int idPassCnt = memberService.idPassCheck(member);
        if (idPassCnt == 0) {
            throw new BaseException(BaseResponseCode.CODE_102);
        }

        // 토큰 생성
        String token = memberService.createToken(member);

        // 토큰 쿠키에 담아줌
        Cookie cookie = new Cookie("jwt", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new BaseResponse();
    }
}
