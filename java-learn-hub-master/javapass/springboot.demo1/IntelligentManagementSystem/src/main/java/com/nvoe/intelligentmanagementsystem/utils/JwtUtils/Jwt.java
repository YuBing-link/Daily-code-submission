package com.nvoe.intelligentmanagementsystem.utils.JwtUtils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Password;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类，用于生成和解析JWT令牌
 */
public class Jwt {

    private static String signKey="itdasknfaksgnasofnaowajnfanskfnopiasfnoasgnkngaenkpl" ;
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 生成的JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                    .addClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, signKey)
                    .setExpiration(new Date(System.currentTimeMillis() + expire))
                    .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                    .setSigningKey(signKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        return claims;
    }
}
