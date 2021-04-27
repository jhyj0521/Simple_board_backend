package jh.SimpleBoard.interceptor;

import jh.SimpleBoard.common.AuthorizationExtractor;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.common.JwtTokenUtil;
import jh.SimpleBoard.configuration.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
public class BearerAuthInterceptor implements HandlerInterceptor {

    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")) {
            log.info("option 메서드인 경우, true 반환");
            return true;
        }

        log.info(">>> BearerAuth interceptor 호출");
        String token = authorizationExtractor.extract(request, "Bearer");
        log.info("jwt: " + token);
        if (ObjectUtils.isEmpty(token)) {
            log.error("토큰 값 비어있음");
            throw new UnauthorizedException(BaseResponseCode.CODE_401);
        }

        if (!jwtTokenUtil.validateToken(token)) {
            log.error("토큰 기간 만료");
            throw new UnauthorizedException(BaseResponseCode.CODE_401);
        }

        String memberPk = jwtTokenUtil.getMemberPk(token);
        request.setAttribute("memberPk", memberPk);

        return true;
    }
}
