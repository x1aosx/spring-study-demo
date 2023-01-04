package com.xiaosx.auth.config;

import com.xiaosx.auth.filer.JwtAuthenticationTokenFilter;
import com.xiaosx.auth.handler.AccessDeniedHandlerImpl;
import com.xiaosx.auth.handler.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    private AuthenticationConfiguration authenticationConfiguration;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //配置过滤
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //登录接口允许匿名访问
                .antMatchers("/auth/login").anonymous()
                // 除去上面外的所有请求全部需要验证
                .anyRequest().authenticated();
        //添加jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加认证和授权失败过滤器
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointImpl())
                .accessDeniedHandler(new AccessDeniedHandlerImpl());
        // 允许跨域
        http.cors();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
