package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Comment;
import jh.SimpleBoard.domain.Criteria;

import java.util.Map;

public interface CommentService {
    long insertComment(Comment comment);

    Comment getComment(long commentNo);

    void deleteComment(long commentNo);

    Map<String, Object> getBoardCommentList(Criteria criteria);
}
