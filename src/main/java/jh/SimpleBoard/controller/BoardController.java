package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시물 작성
     * @param board
     * @param request
     * @return
     */
    @PostMapping("/new")
    public BaseResponse insertBoard(@RequestBody Board board, HttpServletRequest request) {
        // 글제목 필수 체크
        if (isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"글제목"});
        }
        // 글내용 필수 체크
        if (isEmpty(board.getContent())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"글내용"});
        }

        // 인터셉터에서 jwt를 읽고 pk값을 Attribute에 넣어서 받아온다.
        String memberPk = (String) (request.getAttribute("memberPk"));
        long memberNo = Integer.parseInt(memberPk);
        board.setMemberNo(memberNo);

        long boardNo = boardService.insertBoard(board);

        return new BaseResponse();
    }
}
