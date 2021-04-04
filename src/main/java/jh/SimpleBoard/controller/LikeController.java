package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Like;
import jh.SimpleBoard.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static jh.SimpleBoard.common.CommonUtil.getMemberNo;

@RequiredArgsConstructor
@RestController
@RequestMapping("likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/update")
    public BaseResponse clickLike(@RequestBody Like like, HttpServletRequest request) {
        // 글번호 필수 체크
        if (like.getBoardNo() == 0) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"글번호"});
        }

        // request에 저장한 id를 받아와서 Comment 객체에 값을 넣어준다.
        long memberNo = getMemberNo(request);
        like.setMemberNo(memberNo);

        Map<String, Object> data = likeService.clickLike(like);

        return new BaseResponse(data);
    }

}
