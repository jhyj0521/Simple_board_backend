package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {

    long save(Board board);

    Board getBoard(long boardNo);
}
