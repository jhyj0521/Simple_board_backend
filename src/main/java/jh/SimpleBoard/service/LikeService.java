package jh.SimpleBoard.service;

import jh.SimpleBoard.domain.Like;

import java.util.Map;

public interface LikeService {

    Map<String, Object> clickLike(Like like);

}
