package jh.SimpleBoard.serviceImpl;

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
}
