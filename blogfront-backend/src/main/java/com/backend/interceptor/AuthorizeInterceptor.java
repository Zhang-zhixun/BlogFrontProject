package com.backend.interceptor;


import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.mapper.UserAccountMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//拦截器
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Resource
    UserAccountMapper mapper;


    //处理之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取已经通过登录验证的用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        //获取当前已经验证的用户
        Authentication authentication = context.getAuthentication();
        System.out.println("aaaa");

        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        //判断account是否存在
        UserAccount account = mapper.findByUsername(username);
        if(account!=null){
            request.getSession().setAttribute("account",account);
        }
        return true;
    }
}
