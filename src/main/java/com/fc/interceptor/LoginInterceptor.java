package com.fc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

// 登录拦截器
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 后置拦截，登录方法执行完毕后执行
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @param ex 异常
     * @throws Exception 抛出的异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String phone = request.getParameter("phone");

        // 获取Session
        HttpSession session = request.getSession(false);

        // 设置电话号码到Session中
        session.setAttribute(String.valueOf(phone), phone);

        System.out.println("登录过滤器设置的phone：" + phone);
    }
}
