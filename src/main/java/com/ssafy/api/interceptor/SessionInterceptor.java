package com.ssafy.api.interceptor;

import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("login check Interceptor");
        HttpSession session = request.getSession(false);
        if(session == null){
            System.out.println("[interceptor] session 없음");
            Writer out = response.getWriter();
            out.append("login please for the service");

            return false;
        }
        System.out.println("[interceptor] login 성공!");
        return true;
    }
}
