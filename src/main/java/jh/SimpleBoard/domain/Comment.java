package jh.SimpleBoard.domain;

import lombok.Data;

@Data
public class Comment {
    private long commentNo;
    private long boardNo;
    private long memberNo;
    private String memberName;
    private String content;
    private String regDate;
}
