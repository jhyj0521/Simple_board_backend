package jh.SimpleBoard.domain;

import lombok.Data;

@Data
public class Member {
    private long memberNo;
    private String memberId;
    private String memberName;
    private String password;
    private String salt;
    private String regDate;
}
