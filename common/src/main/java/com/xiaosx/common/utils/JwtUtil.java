package com.xiaosx.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;


/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 * 1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "18e3bd97-16dc-481a-aa88-8001b54eeced";

    private final static String ISSUER = "TELECOM";


    /*
     * @author x1aosx
     * @description 生成加密后的密钥 secretKey
     * @date 2022/9/30 11:32
     * @return javax.crypto.SecretKey
     **/
    public static SecretKey generalKey() {
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes());
    }

    /*
     * @author x1aosx
     * @description 生成jwt
     * @date 2022/10/8 16:45
     * @param subject: 用户名
     * @param ttlMillis: 过期时间
     * @param map: 存放信息
     * @return java.lang.String
     **/
    public static String createJWT(String subject, Long ttlMillis, String id) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, null);// 设置过期时间
        return builder.compact();
    }

    public static String createJWT(String subject, String id) {
        JwtBuilder builder = getJwtBuilder(subject, null, id);// 设置过期时间
        return builder.compact();
    }


    /*
     * @author x1aosx
     * @description jwt构造器
     * @date 2022/10/8 16:49
     * @param subject:
     * @param ttlMillis: 过期时间
     * @param id: 用户id
     * @param map: 存放数据
     * @return io.jsonwebtoken.JwtBuilder
     **/
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String id) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        SecretKey secretKey = generalKey();
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder().setId(id)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer(ISSUER)     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /*
     * @author x1aosx
     * @description
     * @date 2022/9/30 11:34
     * @param jwt:
     * @return io.jsonwebtoken.Claims
     **/
    public static Claims parseJWT(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generalKey()).build().parseClaimsJws(jwt).getBody();
    }


    public static void main(String[] args) throws Exception {
        String jwt = createJWT("2123");
        System.out.println("jwt = " + jwt);
    }


}