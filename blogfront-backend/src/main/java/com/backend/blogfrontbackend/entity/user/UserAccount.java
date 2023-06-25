package com.backend.blogfrontbackend.entity.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//生成无参构造方法
@AllArgsConstructor
public class UserAccount {
    private String userAccountUserName;
    private String password;
    private String email;
}
