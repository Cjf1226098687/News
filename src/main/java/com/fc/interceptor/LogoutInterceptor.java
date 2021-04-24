package com.fc.interceptor;

import com.fc.utils.JwtUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class LogoutInterceptor implements HandlerInterceptor {

    /**
     * 后置拦截，退出登录方法执行完毕后执行
     * 通过反射机制修改JWT工具类中的盐值
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器
     * @param ex 异常
     * @throws Exception 抛出的异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Class<?> clazz = Class.forName("com.fc.utils.JwtUtils");

        Field sign = clazz.getDeclaredField("SIGN");

        sign.setAccessible(true);

        Field modifiers = Field.class.getDeclaredField("modifiers");

        modifiers.setAccessible(true);

        modifiers.set(sign, sign.getModifiers() & ~Modifier.FINAL);

        // 使用工具类生成8位随机字符串并修改盐值
        sign.set(null, RandomStringUtils.randomAlphanumeric(8));

        System.out.println("退出登录拦截器");
        System.out.println("重新生成的盐：" + JwtUtils.getSIGN());
    }
}
