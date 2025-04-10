package jpabasic.toyvaserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

/*
1.어떤 경로에 CORS 허용할지 지정.
"/**"는 모든 경로 (/api/**, /ws/** 등 전부) 를 의미함.
즉, Spring 서버의 모든 API에 대해 CORS 허용하는 설정.

2.어떤 출처의 요청을 허용할지 지정.리액트(3000포트)에서 API요청을 허용.

3.OPTIONS 는 Preflight 요청할 때 사용.

4.요청할 때 허용할 헤더 설정.
예를들어 Authorization, Content-Type, X-Request-With 등
JWT, 쿠키, 커스텀 헤더 를사용하려면 필수.

5."쿠키를 포함한 요청 허용 여부" 를 설정
React에서 fetch나 axios로 withCredentials: true 옵션을 줄 때,
여기서도 이 설정을 true로 해줘야 쿠키가 백엔드에 전달됨.

주의: allowedOrigins("*") 와 allowCredentials(true)는
함께 쓰면 에러 발생함.
→ 쿠키를 허용할 땐, * 대신 정확한 origin을 지정해야 함.
*/