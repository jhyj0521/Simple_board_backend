package jh.SimpleBoard.configuration.web.bind.annotation;

import jh.SimpleBoard.common.BaseResponse;
import jh.SimpleBoard.configuration.exception.BaseException;
import jh.SimpleBoard.configuration.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class BaseControllerAdvice {

    private final MessageSource messageSource;

    /**
     * Controller에서 BaseException 발생 시 동작하는 메서드
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    private BaseResponse<?> handleBaseException(BaseException e) {
        return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getResponseCode().name(), e.getArgs(), Locale.KOREAN));
    }

    /**
     * Interceptor에서 UnauthorizedException 발생 시 동작하는 메서드
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private BaseResponse<?> handleUnauthorizedException(UnauthorizedException e) {
        return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getResponseCode().name(), null, Locale.KOREAN));
    }
}
