package jh.SimpleBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Member {

    private long userNo;
    private String userId;
    private String password;
    private Date regDate;
}
