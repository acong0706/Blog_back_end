package com.cong.back_end.config;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.util.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Cong
 * @Create_time 2022-10-16 下午 08:14
 * @Project_name back_end
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        System.out.println(request.getRequestURI());
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        // String token = request.getHeader("token");
        String token = request.getHeader("Authorization");
        // System.out.println(token);
        if (token != null) {
            boolean result = TokenUtils.verify(token);
            if (result) {
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token 验证失败");
            System.out.println("认证失败，未能通过拦截器");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }
}
