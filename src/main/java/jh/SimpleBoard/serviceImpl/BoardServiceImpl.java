package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.mapper.BoardMapper;
import jh.SimpleBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public long insertBoard(Board board) {
        return boardMapper.save(board);
    }
}
