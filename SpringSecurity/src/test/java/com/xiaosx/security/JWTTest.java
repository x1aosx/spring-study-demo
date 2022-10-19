package com.xiaosx.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    public void testBe(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        log.info(passwordEncoder.encode("xiaoshux"));
    }
}
