package com.backend.blogfrontbackend.controller;


import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.service.UserAccountService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//启动验证
@Validated
@RestController
@RequestMapping("/api/user")
public class UserAccountController {

    //邮箱正则表达式
    private final static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";
    private final static String USER_REGEX = "^[a-zA-Z0-9]+$";

    @Resource
    UserAccountService service;

    //注册时验证邮件是否发送成功
    @PostMapping("/valid-register-email")
    public RestBean<String> validateRegisterEmail(@Pattern(regexp = EMAIL_REGEX)
                                          @RequestParam("email") String email, HttpSession session) {
        String hasAccount = "register";
        String s = service.sendValidateEmail(email, session.getId(),hasAccount);
        System.out.println(s);
        if (s == null) {
            System.out.println("邮件已发送，请注意查收");
            return RestBean.success("邮件已发送，请注意查收");
        }else{
            System.out.println("邮件400");
            return RestBean.failure(400, s);
        }
    }

    //重置密码时验证邮件是否发送成功
    @PostMapping("/valid-reset-email")
    public RestBean<String> validateResetEmail(@Pattern(regexp = EMAIL_REGEX)
                                          @RequestParam("email") String email, HttpSession session) {
        String hasAccount = "reset";
        String s = service.sendValidateEmail(email, session.getId(),hasAccount);
        if (s == null)
            return RestBean.success("邮件已发送，请注意查收");
        else{
            System.out.println("邮箱已存在");
            return RestBean.failure(400, s);
        }
    }
    //注册
    @PostMapping("/register")
    public RestBean<String> registerUser(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("code") String code
    ) {
        UserAccount account = new UserAccount();
        account.setUserAccountUserName(username);
        account.setPassword(password);

        String s = service.registerAccount(account, code);
        if (s == null) {
            return RestBean.success("注册成功");
        } else {
            return RestBean.failure(404, s);
        }
    }



    /**
     * 1. 发送邮件已经写好了 验证邮箱验证码
     * 2. 重置密码
     */
    @PostMapping("/start-reset")
    public RestBean<String> startReset(@RequestParam("email") String email,
                                       @RequestParam("code") String code,
                                       HttpSession session){
        String s  =service.validateOnly(email,code,session.getId());
        System.out.println(email);
        if (s == null){
            session.setAttribute("reset-password",email);
            return RestBean.success();
        }else{
            return RestBean.failure(400,s);
        }
    }
    //重置密码
    @PostMapping("/do-reset")
    public RestBean<String> resetPassword(@RequestParam("password") String password,
                                       HttpSession session){
        String email = (String)session.getAttribute("reset-password");
        if (email==null){
            return RestBean.failure(401,"请先完成邮箱验证");
        }else if(service.resetPassword(password,email)){
            session.removeAttribute("reset-password");
            return RestBean.success("密码重置成功");
        }else{
            return RestBean.failure(500,"内部错误,请联系管理员");
        }
    }

    @RequestMapping("/logout")
    public RestBean<String> logoutPage (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1111");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return RestBean.success("登出成功");
    }





}
