package com.backend.blogfrontbackend.controller;


import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.auth.UserAccount;
import com.backend.blogfrontbackend.service.UserAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/role")
public class UserController {

    @Resource
    UserAccountService service;

    @GetMapping("/me")
    public RestBean<UserAccount> me(@SessionAttribute("account") UserAccount user){
        System.out.println(RestBean.success());
        return RestBean.success(user);
    }


}
