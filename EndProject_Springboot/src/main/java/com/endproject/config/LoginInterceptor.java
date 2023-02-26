package com.endproject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WangNaiLinn
 * @Description: 拦截器
 * @date create in 2023/2/2 21:18
 */
@Component   //声明成Bean，HandlerInterceptor定义中就有三个preHandle、postHandle、afterCompletion  直接拷贝出来使用
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("这是preHandle");
        log.info("请求路径："+request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("这是postHandle");
        log.info("请求路径："+request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("这是afterCompletion");
        log.info("请求路径："+request.getRequestURI());
    }
}
