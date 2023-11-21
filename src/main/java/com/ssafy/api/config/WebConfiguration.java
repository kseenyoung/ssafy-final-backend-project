package com.ssafy.api.config;

import com.ssafy.api.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor sessionInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("CORS허용...");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 오리진 요청을 열어줘야함 (포트 주의)
                .allowedMethods("GET","POST") // http 모든 메소드 요청 허용
                .allowedHeaders("*") // 헤더 정보 모두 허용
                .allowCredentials(true); // 쿠키, 세션 정보도 허용, JSESSIONID도 가능
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .excludePathPatterns("/api/auth")
                .excludePathPatterns("");
    }
}
