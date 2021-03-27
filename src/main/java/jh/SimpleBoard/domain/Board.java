package jh.SimpleBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private long boardNo;
    private long memberNo;
    private String memberName;
    private String title;
    private String content;
    private int commentCnt;
    private int likeCnt;
    private Date regDate;
}
