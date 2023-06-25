package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.article.Article;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.concat;

@Mapper
public interface ArticleMapper {
    //查询所有文章信息
    @Select("select * from blog.article")
    List<Article> findByAllArticle();

    //查询分页文章信息，指定起始记录和步长。
//    @Select("select * from blog.article limit #{startNum},#{recordNum}")
//    List<Article> findByArticlePage(int startNum,int recordNum);
    //实现文章表和用户表连接查询
    @Select("select * from blog.article,blog.user where article.uid = user.user_id limit #{startNum},#{recordNum}")
    List<Article> findByArticlePage(int startNum,int recordNum);

    //查询分类文章信息，指定起始位置、步长和文章分类
    @Select("select * from blog.article where category = #{category} limit #{startNum},#{recordNum}")
    List<Article> findByArticleCategory(int startNum,int recordNum,String category);

    //根据关键字对文章标题进行模糊查询
    //模糊查询有两种方式：第一种：CONCAT('%',#{name},'%')第二种"%${name}"
    @Select("select * from blog.article where title like '%${keyString}%' limit #{startNum},#{recordNum}")
    List<Article> findByArticleSearch(int startNum,int recordNum,String keyString);

    //根据作者id查询文章信息
    @Select("select * from blog.article where uid = #{uidNum} limit #{startNum},#{recordNum}")
    List<Article> findByArticleAuthorId(int startNum,int recordNum,int uidNum);
    //根据文章标题查询文章信息
    @Select("select * from blog.article where title = #{title}")
    List<Article> findByArticleTitle(String title);


    //添加文章记录
    @Insert("insert into blog.article(title,content,creat,uid,category) " +
            "values(#{title},#{content},#{creat},#{uid},#{category})")
    int addByArticle(String title,String content,String creat,int uid,String category);

    //修改文章记录
    @Update("update blog.article set title=#{title},content=#{content},category=#{category} where id=#{id}")
    Article reviseArticle(String title,String content,String category,int id);

    //删除文章记录
    @Delete("delete from blog.article where id=#{id}")
    void subArticle(int id);
}
