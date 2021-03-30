package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Board;

import java.util.List;

public interface BoardService {

    long insertBoard(Board board);

    Board getBoard(long boardNo);

    void updateBoard(Board board);

    void deleteBoard(long boardNo);

    List<Board> getBoardList();
}
