package com.xiaosx.springsecurity.config;

import com.xiaosx.springsecurity.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

/**
 * @author x1aosx
 * @classname SpringSecurityConfig.java
 * @description 配置spring security
 * @createTime 2022/09/05 14:20
 */
@Configuration
public class SpringSecurityConfig {

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //配置过滤
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()//关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//关闭session
                .and()
                .authorizeRequests()
                //登录接口允许匿名访问
                .antMatchers("/auth/login").anonymous()
                .anyRequest().authenticated();
        // 允许跨域
        http.cors();
        return http.build();

    }

}
