package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.domain.Criteria;

import java.util.List;
import java.util.Map;

public interface BoardService {

    long insertBoard(Board board);

    Board getBoard(long boardNo);

    void updateBoard(Board board);

    void deleteBoard(long boardNo);

    Map<String, Object> getBoardList(Criteria criteria);
}
