package jh.SimpleBoard.domain;

import lombok.Data;

@Data
public class Criteria {

    // 게시물 번호로 댓글 조회
    private int boardNo;

    // 현재 페이지 번호
    private int currentPageNo;

    // 페이지당 출력할 데이터 개수
    private int recordsPerPage;

    // 검색어
    private String searchWord;

    public Criteria () {
        this.currentPageNo = 1;
        this.recordsPerPage = 10;
    }

    public int getStartPage() {
        return (currentPageNo - 1) * recordsPerPage;
    }
}
