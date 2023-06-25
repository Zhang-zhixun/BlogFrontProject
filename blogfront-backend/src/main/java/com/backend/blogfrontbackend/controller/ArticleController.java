package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.article.Article;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.mapper.ArticleMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Resource
    ArticleMapper articleMapper;
    @GetMapping("/art")
    public List<Article> getArticle(){
        return articleMapper.findByAllArticle();
    }

    @PostMapping("/articlePage")
    public List<Article> getArticlePage(@RequestParam("startNum") int startNum,
                                        @RequestParam("recordNum") int recordNum){
        return articleMapper.findByArticlePage(startNum,recordNum);
    }
    @PostMapping("/articleCategory")
    public List<Article> getArticlePage(@RequestParam("startNum") int startNum,
                                        @RequestParam("recordNum") int recordNum,
                                        @RequestParam("category") String category){
        return articleMapper.findByArticleCategory(startNum,recordNum,category);
    }

    @PostMapping("/articleSearch")
    public List<Article> getArticleSearch(@RequestParam("startNum") int startNum,
                                        @RequestParam("recordNum") int recordNum,
                                        @RequestParam("keyString") String keyString){
        return articleMapper.findByArticleSearch(startNum,recordNum,keyString);
    }

    @PostMapping("/articleAuthorId")
    public List<Article> getArticleAuthorId(@RequestParam("startNum") int startNum,
                                            @RequestParam("recordNum") int recordNum,
                                            @RequestParam("uidNum") int uidNum){
        return articleMapper.findByArticleAuthorId(startNum,recordNum,uidNum);
    }

    @PostMapping("/articleTitle")
    public List<Article> getArticleTitle(@RequestParam("title") String title){
        return articleMapper.findByArticleTitle(title);
    }

    @PostMapping("/articleInsert")
    public int addByArticle(@RequestParam("title") String title,
                                @RequestParam("content") String content,
//                                @RequestParam("create") String creat,
//                                @RequestParam("uid") int uid,
                                @RequestParam("category") String category){
        // 获取当前系统时间
        LocalDateTime now = LocalDateTime.now();
        // 设置时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 转换为字符串
        String creat = now.format(formatter);
        //获取uid的值
        int uid=1;
        return articleMapper.addByArticle(title,content,creat,uid,category);
    }
}
