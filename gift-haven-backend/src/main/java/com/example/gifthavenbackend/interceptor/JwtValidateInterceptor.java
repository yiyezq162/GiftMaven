package com.example.gifthavenbackend.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.gifthavenbackend.common.Result;
import com.example.gifthavenbackend.config.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jwt 拦截器
 *
 * @author 黎锦斌
 * * @date 2023/5/11
 */
@Component
@Slf4j
public class JwtValidateInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        System.out.println(request.getRequestURI() + " 待验证：" + token);
        if (token != null) {
            try {
                jwtUtil.parseToken(token);
                log.debug(request.getRequestURI() + " 放行...");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug(request.getRequestURI() + " 禁止访问...");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.fail(20003, "jwt令牌无效，请重新登录")));
        return false;
    }
}
