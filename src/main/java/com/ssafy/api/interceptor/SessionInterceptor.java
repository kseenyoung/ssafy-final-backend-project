package com.ssafy.api.interceptor;

import com.ssafy.api.user.model.UserLoginDto;
import java.io.Writer;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("login check Interceptor");
        HttpSession session = request.getSession(false);
        Writer out = response.getWriter();
        if(session == null){
            System.out.println("[interceptor] session 없음. 로그인 필요.");
            out.append("login please for the service");

            return false;
        }
        System.out.println("[interceptor] 로그인 되어 있음!");
        UserLoginDto userLoginDto = (UserLoginDto) session.getAttribute("userLoginDto");
        Cookie[] cookies = request.getCookies();

        String cookieUser_id = null;
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("user_id")){
                cookieUser_id = cookie.getValue();
                break;
            }
        }
        return userLoginDto.getUser_id().equals(cookieUser_id);
    }
}
