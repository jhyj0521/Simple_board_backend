package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

    void save(Comment comment);

    Comment getComment(long commentNo);

    void deleteComment(long commentNo);

    void deleteBoardComment(long boardNo);
}
