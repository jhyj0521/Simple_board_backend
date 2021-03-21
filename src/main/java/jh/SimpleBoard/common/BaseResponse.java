package jh.SimpleBoard.common;

import lombok.Data;

import static jh.SimpleBoard.common.BaseResponseCode.CODE_200;

/**
 * 공통으로 사용할 응답 클래스
 */
@Data
public class BaseResponse<T> {

    private BaseResponseCode code;
    private String message;
    private T data;

    /**
     * 정상 처리
     */
    public BaseResponse() {
        this.code = CODE_200;
        this.message = "정상 처리";
    }

    /**
     * 정상 처리
     * @param data - 데이터
     */
    public BaseResponse(T data) {
        this.code = CODE_200;
        this.message = "정상 처리";
        this.data = data;
    }

    /**
     * 에러
     * @param code - 에러 코드
     * @param message - 에러 메시지
     */
    public BaseResponse(BaseResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
