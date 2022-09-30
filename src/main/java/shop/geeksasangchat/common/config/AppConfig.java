package shop.geeksasangchat.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.geeksasangchat.interceptor.AuthenticationInterceptor;
import shop.geeksasangchat.utils.jwt.JwtService;


@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //인터셉터 등록
        registry.addInterceptor(new AuthenticationInterceptor(jwtService, objectMapper))
                .order(0)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**");
    }
}