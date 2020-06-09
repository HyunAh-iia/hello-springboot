package my.study.hello.springboot.config;

import lombok.RequiredArgsConstructor;
import my.study.hello.springboot.config.auth.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolverList) {
        argumentResolverList.add(loginUserArgumentResolver);
        /* [Note]
         * 'HandlerMethodArgumentResolver' always should be added by 'addArgumentResolvers' of 'WebMvcConfigurer'
         */
    }
}
