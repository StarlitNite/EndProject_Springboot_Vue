/*
package com.endproject.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.endproject.service.MenuService;
import com.endproject.service.UserService;
import com.endproject.util.JwtUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/26 16:21
 *//*

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    MenuService menuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token  = request.getHeader("Authorization");

        //

        if (StringUtils.isNotBlank(token)){
            return false;
        }
        //
        Object role_id;
        role_id = JwtUtil.parse(token);
        menuService.MenuTree((Integer) role_id);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
*/
