package com.xiaosx.springsecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.Date;

/**
 * @author x1aosx
 * @classname JWTTest.java
 * @description TODO
 * @createTime 2022/09/06 15:05
 */
@Slf4j
@SpringBootTest
public class JWTTest {
    @Test
    public void testJwt() {
        JwtBuilder jwtBuilder = Jwts.builder().setId("111")
                .setSubject("testJwt")
                .setIssuedAt(new Date());
        String jwt = jwtBuilder.compact();
        log.info(jwt);


    }
}
