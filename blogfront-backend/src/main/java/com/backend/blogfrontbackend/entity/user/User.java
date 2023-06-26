package com.backend.blogfrontbackend.entity.user;


import com.backend.blogfrontbackend.entity.article.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int user_id;
    private String name;
    private String gender;
    private int age;
    private String school;
    private String identity;
    private String user_account_username;
//    private List<Article> articleList;
}
