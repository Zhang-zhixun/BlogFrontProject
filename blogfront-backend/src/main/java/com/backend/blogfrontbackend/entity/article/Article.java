package com.backend.blogfrontbackend.entity.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//生成无参构造方法
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String content;
    private String creat;
    private String category;
    private int uid;

}
