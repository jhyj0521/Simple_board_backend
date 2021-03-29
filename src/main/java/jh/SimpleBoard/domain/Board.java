package jh.SimpleBoard.domain;

import lombok.Data;

@Data
public class Board {
    private long boardNo;
    private long memberNo;
    private String memberName;
    private String title;
    private String content;
    private int commentCnt;
    private int likeCnt;
    private String regDate;
}
