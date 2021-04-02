package jh.SimpleBoard.mapper;

import jh.SimpleBoard.domain.Comment;
import jh.SimpleBoard.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    void save(Comment comment);

    Comment getComment(long commentNo);

    void deleteComment(long commentNo);

    void deleteBoardComment(long boardNo);

    List<Comment> getBoardCommentList(Criteria criteria);

    int getBoardCommentTotalCnt(long boardNo);

}
