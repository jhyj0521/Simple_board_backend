package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.common.CommonUtil;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Comment;
import jh.SimpleBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static jh.SimpleBoard.common.CommonUtil.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/new")
    public BaseResponse insertComment(@RequestBody Comment comment, HttpServletRequest request) {
        // 글번호 필수 체크
        if (comment.getBoardNo() == 0) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"글번호"});
        }
        // 댓글내용 필수 체크
        if (isEmpty(comment.getContent())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"댓글내용"});
        }

        // request에 저장한 id를 받아와서 Comment 객체에 값을 넣어준다.
        long memberNo = getMemberNo(request);
        comment.setMemberNo(memberNo);

        long commentNo = commentService.insertComment(comment);
        Map<String, Object> data = new HashMap();
        data.put("commentNo", commentNo);

        return new BaseResponse(data);
    }
}
