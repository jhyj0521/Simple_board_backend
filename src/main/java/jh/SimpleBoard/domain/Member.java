package jh.SimpleBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Member {

    private long memberNo;
    private String memberId;
    private String memberName;
    private String password;
    private String salt;
    private Date regDate;
}
