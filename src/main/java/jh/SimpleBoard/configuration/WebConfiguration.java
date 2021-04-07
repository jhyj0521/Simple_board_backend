package jh.SimpleBoard.configuration;

import jh.SimpleBoard.common.AuthorizationExtractor;
import jh.SimpleBoard.common.JwtTokenUtil;
import jh.SimpleBoard.interceptor.BaseHandlerInterceptor;
import jh.SimpleBoard.interceptor.BearerAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        source.setCacheSeconds(60);
        source.setDefaultLocale(Locale.KOREAN);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public BaseHandlerInterceptor baseHandlerInterceptor() {
        return new BaseHandlerInterceptor();
    }

    @Bean
    public BearerAuthInterceptor bearerAuthInterceptor() {
        return new BearerAuthInterceptor(authorizationExtractor(), jwtTokenUtil());
    }

    @Bean
    public AuthorizationExtractor authorizationExtractor() {
        return new AuthorizationExtractor();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseHandlerInterceptor());
        registry.addInterceptor(bearerAuthInterceptor()).excludePathPatterns("/members/*");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 uri에 대해 http://localhost:3000의 접근을 허용한다.
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }
}
