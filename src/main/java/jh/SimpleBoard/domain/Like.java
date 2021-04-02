package jh.SimpleBoard.domain;

import lombok.Data;

@Data
public class Like {
    private long likeNo;
    private long boardNo;
    private long memberNo;
    private String likeYn;
}
