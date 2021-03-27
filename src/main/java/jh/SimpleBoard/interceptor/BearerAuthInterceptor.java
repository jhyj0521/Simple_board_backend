package jh.SimpleBoard.interceptor;

import jh.SimpleBoard.common.AuthorizationExtractor;
import jh.SimpleBoard.common.BaseResponseCode;
import jh.SimpleBoard.common.JwtTokenUtil;
import jh.SimpleBoard.configuration.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {

    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = authorizationExtractor.extract(request, "Bearer");
        if (ObjectUtils.isEmpty(token)) {
            return true;
        }

        if (!jwtTokenUtil.validateToken(token)) {
            throw new UnauthorizedException(BaseResponseCode.CODE_401);
        }

        String memberPk = jwtTokenUtil.getMemberPk(token);
        request.setAttribute("memberPk", memberPk);

        return true;
    }
}
