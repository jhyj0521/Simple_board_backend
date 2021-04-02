package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Comment;
import jh.SimpleBoard.domain.Criteria;
import jh.SimpleBoard.mapper.BoardMapper;
import jh.SimpleBoard.mapper.CommentMapper;
import jh.SimpleBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final BoardMapper boardMapper;

    @Override
    public long insertComment(Comment comment) {
        // 댓글 추가 시 댓글 수 1 증가
        commentMapper.save(comment);
        boardMapper.updateCommentCnt(comment.getBoardNo(), 1);
        return comment.getCommentNo();
    }

    @Override
    public Comment getComment(long commentNo) {
        return commentMapper.getComment(commentNo);
    }

    @Override
    public void deleteComment(long commentNo) {
        // 댓글 번호로 게시글 번호 조회해서 댓글 삭제 시 댓글 수 1 감소
        long boardNo = commentMapper.getComment(commentNo).getBoardNo();
        commentMapper.deleteComment(commentNo);
        boardMapper.updateCommentCnt(boardNo, -1);
    }

    @Override
    public Map<String, Object> getBoardCommentList(Criteria criteria) {
        Map<String, Object> result = new HashMap<>();

        List<Comment> list = commentMapper.getBoardCommentList(criteria);
        int totalCnt = commentMapper.getBoardCommentTotalCnt(criteria.getBoardNo());

        result.put("list", list);
        result.put("totalCnt", totalCnt);

        return result;
    }
}
