package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.common.CommonUtil;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static jh.SimpleBoard.common.CommonUtil.getMemberNo;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 생성
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

        // request에 저장한 id를 받아와서 Board 객체에 값을 넣어준다.
        long memberNo = getMemberNo(request);
        board.setMemberNo(memberNo);

        long boardNo = boardService.insertBoard(board);
        Map<String, Object> ret = new HashMap();
        ret.put("boardNo", boardNo);

        return new BaseResponse(ret);
    }

    /**
     * 게시글 수정
     * @param boardNo
     * @param board
     * @param request
     * @return
     */
    @PostMapping("/{boardNo}/edit")
    public BaseResponse updateBoard(@PathVariable("boardNo") long boardNo, @RequestBody Board board, HttpServletRequest request) {

        // 글제목 필수 체크
        if (isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"글제목"});
        }
        // 글내용 필수 체크
        if (isEmpty(board.getContent())) {
            throw new BaseException(BaseResponseCode.CODE_100, new String[]{"글내용"});
        }

        // 현재 로그인한 회원 번호와 게시글 작성한 회원 번호를 비교해서 다른 경우에 예외 발생
        Board info = boardService.getBoard(boardNo);
        long memberNo = getMemberNo(request);
        if (memberNo != info.getMemberNo()) {
            throw new BaseException(BaseResponseCode.CODE_103);
        }

        board.setBoardNo(boardNo);
        boardService.updateBoard(board);

        return new BaseResponse();
    }

    /**
     * 게시글 삭제
     * @param boardNo
     * @param request
     * @return
     */
    @PostMapping("/{boardNo}/delete")
    public BaseResponse deleteBoard(@PathVariable("boardNo") long boardNo, HttpServletRequest request) {

        // 현재 로그인한 회원 번호와 게시글 작성한 회원 번호를 비교해서 다른 경우에 예외 발생
        Board info = boardService.getBoard(boardNo);
        long memberNo = getMemberNo(request);
        if (memberNo != info.getMemberNo()) {
            throw new BaseException(BaseResponseCode.CODE_103);
        }

        boardService.deleteBoard(boardNo);

        return new BaseResponse();
    }

    /**
     * 게시글 세부 조회
     * @param boardNo
     * @return
     */
    @GetMapping("/{boardNo}")
    public BaseResponse getBoard(@PathVariable("boardNo") long boardNo) {

        Board info = boardService.getBoard(boardNo);
        Map<String, Object> ret = new HashMap<>();
        ret.put("info", info);

        return new BaseResponse(ret);
    }
}
