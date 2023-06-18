package com.backend.blogfrontbackend.config;

import com.backend.interceptor.AuthorizeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //@Rouse AuthorizeInterceptor authorizeInterceptor()
    /**
     *所出问题：
     * 写登录认证拦截器的时候碰到一个小问题：在拦截器中如果没有用到mapper、service时，一切正常;
     * 但是一旦用到mapper、service，就报空指针异常，
     * 一开始以为是没有查到数据（事实上没查到和查不了是两回事~~只是简单没查到的话也应该返回一个空对象），
     * 不过通过debug，发现是mapper为空，也就是根本没有注入！！
     * 先后检查了mapper、interceptor有没有分别加上@Mapper、@Component，发现都解决不了。
     *
     *解决方案：
     *  1.将 @Rouse AuthorizeInterceptor authorizeInterceptor()注释
     *  2.将拦截器注成bean交给Spring托管
     * */
    @Bean
    public AuthorizeInterceptor authorizeInterceptor(){
        return new AuthorizeInterceptor();
    }

    //
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(authorizeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/**")
                .excludePathPatterns("/api/index/**")
                .excludePathPatterns("http://localhost:2223/api/index/upload");
    }
}
