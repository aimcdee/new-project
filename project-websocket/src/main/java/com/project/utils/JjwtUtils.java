package com.project.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @author liangyuding
 * @create 2020-04-15
 */
public class JjwtUtils {

    public static final String JJWT_SECRETKEY = "zj.car@2020";
    public static final String JJWT_KEY_USERID = "userId";

    /**
     * 根据token 获取用户ID
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return Long.valueOf((Integer) claims.get(JJWT_KEY_USERID));
        }
        return null;
    }

    /**
     * 获取token到期时间
     *
     * @param token
     * @return
     */
    public static Date getExpiration(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getExpiration();
        }
        return null;
    }

    /**
     * 获取token 登录时间
     *
     * @param token
     * @return
     */
    public static Date getIssuedAt(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getIssuedAt();
        }
        return null;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        return getClaims(token) != null;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    private static Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(JJWT_SECRETKEY).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQsIm5hbWUiOiLlj7Llro_mnbAiLCJzY2hvb2xJZCI6MSwiaWF0IjoxNTYwNzQyNzkzLCJleHAiOjE1NjExNzQ3OTN9.8937de24W1o6m6IKsYvM2BoP5CbEAQ-es6W9EaWfTEQ";
        System.out.println(getUserId(token));
    }

}
