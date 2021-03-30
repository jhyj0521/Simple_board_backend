package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.mapper.BoardMapper;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }
}
