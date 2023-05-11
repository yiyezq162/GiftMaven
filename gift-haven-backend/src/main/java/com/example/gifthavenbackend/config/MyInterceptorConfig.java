package com.example.gifthavenbackend.config;

import com.example.gifthavenbackend.interceptor.JwtValidateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 配置拦截器
 *
 * @author 黎锦斌
 * * @date 2023/5/11
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Resource
    private JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtValidateInterceptor);
        registration.addPathPatterns("/**")
                .excludePathPatterns(
                        "/admin/login",
                        "/admin/info",
                        "/admin/logout",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**",
                        "/gifts/**");
    }
}

