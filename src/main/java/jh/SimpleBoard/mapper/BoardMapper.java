package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    long save(Board board);

    Board getBoard(long boardNo);

    void updateBoard(Board board);

    void deleteBoard(long boardNo);

    List<Board> getBoardList();
}
