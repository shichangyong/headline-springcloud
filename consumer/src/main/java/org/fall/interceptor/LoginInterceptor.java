package org.fall.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 拦截器，用来在未登录时访问受保护页面时进行拦截并抛出AccessForbiddenException
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 通过request获得session对象
        HttpSession session = request.getSession();

        // 从session域中取出Admin对象

        // admin对象不为空，表示已登录，放行
        return true;
    }
}
