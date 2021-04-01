package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Comment;
import jh.SimpleBoard.mapper.CommentMapper;
import jh.SimpleBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public long insertComment(Comment comment) {
        commentMapper.save(comment);
        return comment.getCommentNo();
    }

    @Override
    public Comment getComment(long commentNo) {
        return commentMapper.getComment(commentNo);
    }

    @Override
    public void deleteComment(long commentNo) {
        commentMapper.deleteComment(commentNo);
    }
}
