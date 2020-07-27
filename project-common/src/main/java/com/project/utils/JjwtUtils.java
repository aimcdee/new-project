package com.project.utils;

import com.project.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author liangyuding
 * @date 2020-03-10
 */
public class JjwtUtils {

    /**
     * token有效时长
     */
    // TODO application.yml 配置不生效
    private static long expire = 86400;

    /**
     * 续命时长
     */
    private static long prolong = 7200;

    public static String createToken(Long userId, String name, String phone) {
        Date date = new Date(System.currentTimeMillis());
        Claims claims = Jwts.claims();
        claims.put(Constant.JJWT_KEY_USERID, userId);
        claims.put(Constant.JJWT_KEY_NAME, name);
        claims.put(Constant.JJWT_KEY_PHONE, phone);

        //失效时间
        Date expiration = DateUtils.addDateMinutes(date, Constant.LOGIN_EXPIRES);
        System.out.println(DateUtils.format(expiration));
        String compact = Jwts.builder()
            .setId(String.valueOf(userId))
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, Constant.JJWT_SECRETKEY)
            // issued at，是指jwt的发行时间
            .setIssuedAt(date)
            .setExpiration(expiration)
            .compact();
        return compact;
    }

    /**
     * 根据token 获取用户ID
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return Long.valueOf((Integer) claims.get(Constant.JJWT_KEY_USERID));
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
        return Jwts.parser().setSigningKey(Constant.JJWT_SECRETKEY).parseClaimsJws(token).getBody();
    }

    public static long getProlong() {
        return prolong;
    }

    public static void main(String[] args) {
        String jwtToken = createToken(9L, "展示", "13888888888");
        System.out.println("token:" + jwtToken);
        Claims claims = Jwts.parser().setSigningKey(Constant.JJWT_SECRETKEY).parseClaimsJws(jwtToken).getBody();
        System.out.println("claims:" + claims);
        System.out.println("" + claims.getId());
        System.out.println("dealUserId:" + claims.get("dealUserId"));
        System.out.println("" + getUserId(jwtToken));
        System.out.println("" + claims.getIssuedAt());
        System.out.println("" + claims.getExpiration());

    }
}
