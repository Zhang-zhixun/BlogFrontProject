package com.backend.blogfrontbackend.service;

import com.backend.blogfrontbackend.entity.user.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    //发送邮箱验证码
    String sendValidateEmail(String email,String sessionId,String hasAccount);
    //注册
    String registerAccount(UserAccount userAccount, String code);
    //重置密码时 验证邮件验证码
    String validateOnly(String email,String code,String sessionId);
    //重置密码
    boolean resetPassword(String password,String email);

}
