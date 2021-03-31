package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.domain.Criteria;
import jh.SimpleBoard.mapper.BoardMapper;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
