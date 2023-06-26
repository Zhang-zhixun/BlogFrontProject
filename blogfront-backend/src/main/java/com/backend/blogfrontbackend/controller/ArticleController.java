package com.backend.blogfrontbackend.controller;

import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.article.Article;
import com.backend.blogfrontbackend.entity.user.User;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.mapper.ArticleMapper;
import com.backend.blogfrontbackend.mapper.UserMapper;
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
    //查询所有文章信息
    @GetMapping("/art")
    public List<Article> getArticle(){
        return articleMapper.findByAllArticle();
    }

    //查询分页文章信息，指定起始记录和步长。实现文章表和用户表连接查询
    @PostMapping("/articlePage")
    public List<Article> getArticlePage(@RequestParam("startNum") int startNum,
                                        @RequestParam("recordNum") int recordNum){
        return articleMapper.findByArticlePage(startNum,recordNum);
    }

    //查询分类文章信息，指定起始位置、步长和文章分类
    @PostMapping("/articleCategory")
    public List<Article> getArticlePage(@RequestParam("startNum") int startNum,
                                        @RequestParam("recordNum") int recordNum,
                                        @RequestParam("category") String category){
        return articleMapper.findByArticleCategory(startNum,recordNum,category);
    }

    //根据关键字对文章标题进行模糊查询
    @PostMapping("/articleSearch")
    public List<Article> getArticleSearch(@RequestParam("startNum") int startNum,
                                        @RequestParam("recordNum") int recordNum,
                                        @RequestParam("keyString") String keyString){
        return articleMapper.findByArticleSearch(startNum,recordNum,keyString);
    }

    //根据作者id查询文章信息
    @PostMapping("/articleAuthorId")
    public List<Article> getArticleAuthorId(@RequestParam("startNum") int startNum,
                                            @RequestParam("recordNum") int recordNum,
                                            @RequestParam("uidNum") int uidNum){
        return articleMapper.findByArticleAuthorId(startNum,recordNum,uidNum);
    }

    //根据文章id查询文章信息
    @PostMapping("/articleTitle")
    public List<Article> getArticleTitle(@RequestParam("aidNum") int aidNum){
        return articleMapper.findByArticleTitle(aidNum);
    }

    //添加文章记录
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

    //修改文章记录
    @PostMapping("/articleUpdate")
    public int updateByArticle(@RequestParam("aidNum") int aidNum,
                               @RequestParam("title") String title,
                               @RequestParam("category") String category,
                               @RequestParam("content") String content){
        return articleMapper.updateArticle(aidNum,title,category,content);
    }

    //删除文章记录
    @PostMapping("/articleDelete")
    public int deleteByArticle(@RequestParam("aidNum") int aidNum){
        return articleMapper.deleteArticle(aidNum);
    }

}
