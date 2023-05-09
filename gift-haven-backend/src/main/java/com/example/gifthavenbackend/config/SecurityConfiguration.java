package com.example.gifthavenbackend.config;

import com.alibaba.fastjson.JSONObject;
import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.service.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author aa
 * TODO 不知道有没有机会用上这个框架进行验证，先完成基础架构吧
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

    @Resource
    private AdminService adminService;

    @Resource
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterCain(HttpSecurity httpSecurity, PersistentTokenRepository repository) throws Exception {
        return httpSecurity
                //用于配置请求访问权限的规则
                .authorizeRequests()
                .anyRequest().authenticated().and()
                //用于配置表单登录的相关信息
                .formLogin().loginProcessingUrl("/admin/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                //记住我
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(repository)
                .tokenValiditySeconds(3600 * 27 * 7)
                .and()
                .logout().logoutUrl("/admin/logout")
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
                //用于防止跨站请求伪造攻击（CSRF）的相关设置
                .csrf().disable()
                //配置跨域
                .cors()
                .configurationSource(this.configurationSource())
                .and()
                .exceptionHandling().authenticationEntryPoint(this::onAuthenticationFailure).and().build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    /**
     * 设置跨域访问源
     */
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration core = new CorsConfiguration();
        //只对这个请求地址开放
        core.addAllowedOriginPattern("*");
        core.setAllowCredentials(true);
        core.addAllowedHeader("*");
        core.addAllowedMethod("*");
        core.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", core);
        return source;
    }

    private void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("UTF-8");
        if (request.getRequestURI().endsWith("/login")) {
            response.getWriter().write(JSONObject.toJSONString(Result.success("登录成功")));
        } else {
            response.getWriter().write(JSONObject.toJSONString(Result.success("退出登录成功")));

        }

    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(Result.fail(401, exception.getMessage())));

    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
        return security
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(adminService)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
