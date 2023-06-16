package com.example.blogfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    String index(){
        return "index";
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

}
