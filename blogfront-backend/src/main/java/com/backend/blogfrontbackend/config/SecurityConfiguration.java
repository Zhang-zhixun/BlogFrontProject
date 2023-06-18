package com.backend.blogfrontbackend.config;

import com.alibaba.fastjson.JSONObject;
import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.service.UserAccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Resource
    UserAccountService userAccountService;


    @Resource
    DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,PersistentTokenRepository repository) throws Exception {
        return http
                //权限校验
                .authorizeHttpRequests()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/api/index/**").permitAll()
                //验证所有请求
                .anyRequest().authenticated()
                .and()
                //表单登录
                .formLogin().loginProcessingUrl("/api/user/login")
                //登录成功
                .successHandler(this::onAuthenticationSuccess)
                //登录失败
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout().logoutUrl("/api/user/loginout")
                //设置权限 登出成功
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
                //记住密码
                .rememberMe()
                //因为remeberme默认字段是remeber-me 跟前端renmeber不一样 所以需要修改获取命名
                .rememberMeParameter("remember")
                //配置token
                .tokenRepository(repository)
                //配置rememberme的保留时间限制
                .tokenValiditySeconds(3600 * 24 * 7)
                .and()
                //验证登录
                .userDetailsService(userAccountService)
                //
                .csrf().disable()
                //跨域配置
                .cors()
                .configurationSource(this.configurationSource())
                .and()
                //异常 没有权限
                .exceptionHandling().authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .build();

    }


    @Bean
    public PersistentTokenRepository tokenRepository() {
        //创建jdbc 设置数据库的数据源
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //如果没有存储表就setCreateTableOnStartup()设置为true 创建token表 存放记住密码的账号和密码
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;

    }


    //密码加密验证
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //配置跨域的源
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        //运行访问所有的跨域请求 但是项目上线不行这样 不安全 只能运行自己的服务器
        //cors.addAllowedOriginPattern("http://localhost:5173/");
        cors.addAllowedOriginPattern("*");
        //允许携带cookie
        cors.setAllowCredentials(true);
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        cors.addExposedHeader("*");
        //创建一个UrlBasedCorsConfigurationSource源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //所有请求走cors的政策
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        //判断传来的请求
        if (request.getRequestURI().endsWith("/login"))
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));
        else if (request.getRequestURI().endsWith("/loginout"))
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出登录")));
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401, exception.getMessage())));

    }

}
