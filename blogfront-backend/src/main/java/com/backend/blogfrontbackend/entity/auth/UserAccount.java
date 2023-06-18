package com.backend.blogfrontbackend.entity.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    private String userAccountUserName;
    private String password;
    private String email;
}
