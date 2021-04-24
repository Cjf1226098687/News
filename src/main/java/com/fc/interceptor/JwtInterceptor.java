package com.fc.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Jwt登录验证拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("JWT拦截器执行");

        // 从请求参数中获取token
        String token = request.getParameter("token");

        // 获取Jackson核心处理对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 设置响应内容类型
        response.setContentType("application/json; charset=UTF-8");

        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("success", false);
        map.put("data", null);

        if (token == null || token.equals("") || token.equals("null")) {
            map.put("message", "token为空");

            // 对象转Json字符串
            String json = objectMapper.writeValueAsString(map);

            // 发送响应
            response.getWriter().println(json);
        } else {
            try {
                // 解析token并获取载荷
                Map<String, Object> claim = JwtUtils.getClaim(token);

                // 设置到域对象中
                request.setAttribute("claim", claim);

                // 从claim中获取电话号码
                String result = String.valueOf(claim.get("phone"));

                // 获取Session
                HttpSession session = request.getSession(false);

                System.out.println("Session获取的phone：" + result);

                // 如果session中存储的有这个id，那就说明登录成功
                if (session.getAttribute(result) != null) {
                    // 放行
                    return true;
                } else {
                    map.put("message", "token已失效，请重新登录");
                }

                // 抛出对应的异常
            } catch (AlgorithmMismatchException e) {
                map.put("message", "算法不匹配");
            } catch (InvalidClaimException e) {
                map.put("message", "非法载荷");
            } catch (SignatureVerificationException e) {
                map.put("message", "签名不匹配");
            } catch (TokenExpiredException e) {
                map.put("message", "token已过期");
            } catch (Exception e) {
                map.put("message", "token异常，访问被终止");
            }

            // 对象转Json字符串
            String json = objectMapper.writeValueAsString(map);

            // 发送响应
            response.getWriter().println(json);
        }

        return false;
    }
}
