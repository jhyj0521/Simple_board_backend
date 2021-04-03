package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Like;
import jh.SimpleBoard.mapper.LikeMapper;
import jh.SimpleBoard.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;

    @Override
    public Like getLike(Like like) {
        return likeMapper.getLike(like);
    }
}
