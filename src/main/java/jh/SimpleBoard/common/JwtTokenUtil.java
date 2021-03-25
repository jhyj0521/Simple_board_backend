package jh.SimpleBoard.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenUtil {
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private long tokenValidMillisecond = 1000L * 60 * 60;

    //토큰 생성
    public String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + tokenValidMillisecond);

        return Jwts.builder()
                .setClaims(claims)  // 토큰 정보 설정
                .setIssuedAt(now)   // 토큰 발급 시간 설정
                .setExpiration(expiration)    // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    //유효한 토큰인지 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
