package jh.SimpleBoard.serviceImpl;

import jh.SimpleBoard.domain.Like;
import jh.SimpleBoard.mapper.LikeMapper;
import jh.SimpleBoard.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;

    @Override
    public Map<String, Object> clickLike(Like like) {
        Map<String, Object> result = new HashMap<>();

        // 회원 번호와 게시글 번호로 좋아요 DB를 조회하여 Like 객체 반환
        Like memberLike = likeMapper.getLike(like);

        // Like 객체가 없을 경우, Like 객체 추가한 뒤 반환
        if (ObjectUtils.isEmpty(memberLike)) {
            like.setLikeYn("Y");
            likeMapper.insertLike(like);
            result.put("likeYn", like.getLikeYn());
            return result;
        }

        // Like 객체가 있을 경우, likeYn 필드를 "Y"는 "N"으로 "N"은 "Y"로 하여 업데이트
        if (memberLike.getLikeYn().equals("Y")) {
            like.setLikeYn("N");
        } else {
            like.setLikeYn("Y");
        }

        // likeNo로 조회하기 위해, 가져온 Like 객체의 likeNo 값을 대입
        like.setLikeNo(memberLike.getLikeNo());
        likeMapper.updateLike(like);
        result.put("likeYn", like.getLikeYn());

        return result;
    }
}
