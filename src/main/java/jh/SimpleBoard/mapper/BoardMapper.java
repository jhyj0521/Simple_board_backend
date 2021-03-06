package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Board;
import jh.SimpleBoard.domain.Criteria;
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

    List<Board> getBoardList(Criteria criteria);

    int getTotalCnt();

    List<Board> getSearchList(Criteria criteria);

    int getSearchTotalCnt(String searchWord);

    void updateCommentCnt(long boardNo, int amount);

    void updateLikeCnt(long boardNo, int amount);
}
