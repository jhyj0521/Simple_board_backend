package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikeMapper {
    Like getLike(Like like);
}
