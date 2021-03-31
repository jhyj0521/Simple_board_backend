package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.domain.Criteria;
import jh.SimpleBoard.mapper.BoardMapper;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public long insertBoard(Board board) {
        boardMapper.save(board);
        return board.getBoardNo();
    }

    @Override
    public Board getBoard(long boardNo) {
        return boardMapper.getBoard(boardNo);
    }

    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(long boardNo) {
        boardMapper.deleteBoard(boardNo);
    }

    @Override
    public Map<String, Object> getBoardList(Criteria criteria) {
        Map<String, Object> result = new HashMap<>();

        List<Board> list = boardMapper.getBoardList(criteria);
        int totalCnt = boardMapper.getTotalCnt();

        result.put("totalCnt", totalCnt);
        result.put("list", list);

        return result;
    }

    @Override
    public Map<String, Object> getSearchList(Criteria criteria) {
        Map<String, Object> result = new HashMap<>();
        List<Board> list = new ArrayList<>();

        // 검색어가 없는 경우에는 빈 리스트와 개수 반환
        if (ObjectUtils.isEmpty(criteria.getSearchWord())) {
            result.put("totalCnt", 0);
            result.put("list", list);

            return result;
        }

        list = boardMapper.getSearchList(criteria);
        int totalCnt = boardMapper.getSearchTotalCnt(criteria.getSearchWord());

        result.put("totalCnt", totalCnt);
        result.put("list", list);

        return result;
    }
}
