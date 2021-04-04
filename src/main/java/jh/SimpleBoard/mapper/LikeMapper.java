package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikeMapper {
    Like getLike(Like like);

    void insertLike(Like like);

    void updateLike(Like like);

    void deleteBoardLike(long boardNo);

    String getLikeYn(long boardNo, long memberNo);
}
