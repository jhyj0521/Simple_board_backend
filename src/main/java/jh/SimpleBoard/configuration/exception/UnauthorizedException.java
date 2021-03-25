package jh.SimpleBoard.configuration.exception;

import jh.SimpleBoard.common.BaseResponseCode;

public class UnauthorizedException extends AbstractBaseException {
    private static final long serialVersionUID = 8342235231880246631L;

    public UnauthorizedException() {
    }

    public UnauthorizedException(BaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
