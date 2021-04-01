package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Comment;

public interface CommentService {
    long insertComment(Comment comment);

    Comment getComment(long commentNo);

    void deleteComment(long commentNo);

}
