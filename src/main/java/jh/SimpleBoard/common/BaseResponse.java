package jh.SimpleBoard.common;

import lombok.Data;

/**
 * 공통으로 사용할 응답 클래스
 */
@Data
public class BaseResponse <T> {

    private BaseResponseCode code;
    private String message;
    private T data;

    public BaseResponse() {
        this.code = BaseResponseCode.SUCCESS;
    }

    public BaseResponse(T data) {
        this.code = BaseResponseCode.SUCCESS;
        this.data = data;
    }

    public BaseResponse(BaseResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
