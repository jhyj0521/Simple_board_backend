package jh.SimpleBoard.controller;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.domain.Criteria;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        Map<String, Object> data = new HashMap();
        data.put("boardNo", boardNo);

        return new BaseResponse(data);
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
     * 게시글과 게시글에 있는 댓글과 좋아요 정보까지 삭제
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
     * @param request
     * @return
     */
    @GetMapping("/{boardNo}")
    public BaseResponse getBoard(@PathVariable("boardNo") long boardNo, HttpServletRequest request) {

        long memberNo = getMemberNo(request);
        Board board = boardService.getBoard(boardNo);
        String likeYn = boardService.getBoardLikeYn(boardNo, memberNo);

        Map<String, Object> info = new LinkedHashMap<>();
        info.put("boardNo", board.getBoardNo());
        info.put("memberNo", board.getMemberNo());
        info.put("memberName", board.getMemberName());
        info.put("title", board.getTitle());
        info.put("content", board.getContent());
        info.put("commentCnt", board.getCommentCnt());
        info.put("likeCnt", board.getLikeCnt());
        info.put("likeYn", likeYn);
        info.put("regDate", board.getRegDate());

        Map<String, Object> data = new HashMap<>();
        data.put("info", info);

        return new BaseResponse(data);
    }

    /**
     * 게시글 목록 조회
     * @param criteria
     * @return
     */
    @GetMapping("/lists")
    public BaseResponse getBoardList(@ModelAttribute Criteria criteria) {

        // 리턴 값의 순서를 보장하기 위해 LinkedHashMap으로 구현
        Map<String, Object> data = new LinkedHashMap<>();

        // 페이지 당 레코드 개수와, 해당 페이지 번호 정보를 받아서 페이징 처리를 하여 목록 조회
        Map<String, Object> result = boardService.getBoardList(criteria);

        data.put("currentPageNo", criteria.getCurrentPageNo());
        data.put("recordsPerPage", criteria.getRecordsPerPage());
        data.put("totalCnt", result.get("totalCnt"));
        data.put("list", result.get("list"));
        return new BaseResponse(data);
    }

    /**
     * 게시글 검색
     * @param criteria
     * @return
     */
    @GetMapping("/searchLists")
    public BaseResponse getSearchList(@ModelAttribute Criteria criteria) {

        // 리턴 값의 순서를 보장하기 위해 LinkedHashMap으로 구현
        Map<String, Object> data = new LinkedHashMap<>();

        Map<String, Object> result = boardService.getSearchList(criteria);

        data.put("searchWord", criteria.getSearchWord());
        data.put("currentPageNo", criteria.getCurrentPageNo());
        data.put("recordsPerPage", criteria.getRecordsPerPage());
        data.put("totalCnt", result.get("totalCnt"));
        data.put("list", result.get("list"));

        return new BaseResponse(data);
    }
}
