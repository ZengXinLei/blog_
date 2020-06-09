package com.example.mapping;

import com.example.enity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: zxl
 * @Time: 2020/05/27 14:06:39
 * @system: ASUS
 **/
@Component
public interface ArticleMapping {

    @Select({
            "select * from article where aState='true' limit #{start},#{offset}"
    })
    @Results({
            @Result(property = "aUserId",column = "aUserId"),
            @Result(property = "aId",column = "aId"),
            @Result(property = "aClickCount",column = "aId", one = @One(select = "com.example.mapping.GiveMapping.likeCount")),
            @Result(property = "aReadCount",column = "aId",one = @One(select = "com.example.mapping.LookedMapping.readCount")),
            @Result(property = "aReviewCount",column = "aId",one = @One(select = "com.example.mapping.CommentMapping.getCommentCount")),
            @Result(property = "user",column = "aUserId", one = @One(select = "com.example.mapping.UserMapping.getUserById"))
    })
    List<Article> getArticleList(@Param("start") int s,@Param("offset") int o);

    @Select({
            "select * from article where aState='true' and aType like #{aType} limit #{start},#{offset}"
    })
    @Results({

            @Result(property = "aUserId",column = "aUserId"),
            @Result(property = "aId",column = "aId"),
            @Result(property = "aClickCount",column = "aId", one = @One(select = "com.example.mapping.GiveMapping.likeCount")),
            @Result(property = "aReadCount",column = "aId",one = @One(select = "com.example.mapping.LookedMapping.readCount")),
            @Result(property = "aReviewCount",column = "aId",one = @One(select = "com.example.mapping.CommentMapping.getCommentCount")),
            @Result(property = "user",column = "aUserId", one = @One(select = "com.example.mapping.UserMapping.getUserById"))

    })
    List<Article> getArticleListByTag(@Param("start") int s,@Param("offset") int o,@Param("aType") String tag);

    @Select({
            "select count(*) from article where aState='true'"
    })
    int getArticlePages();


    @Select({
            "select count(*) from article where aState='true' and aType like #{aType}"
    })
    int getArticlePagesByTag(@Param("aType") String tag);

    @Select({
            "select * from article where aId=#{aId} and aState='true'"
    })
    @Results({
            @Result(property = "aUserId",column = "aUserId"),
            @Result(property = "aId",column = "aId"),
            @Result(property = "aClickCount",column = "aId", one = @One(select = "com.example.mapping.GiveMapping.likeCount")),
            @Result(property = "aReviewCount",column = "aId",one = @One(select = "com.example.mapping.CommentMapping.getCommentCount")),
            @Result(property = "user",column = "aUserId", one = @One(select = "com.example.mapping.UserMapping.getUserById"))
    })
    Article getArticleById(@Param("aId") int aId);

    @Update({
            "update article set aTitle=#{aTitle},aText=#{aText},aHtml=#{aHtml},aTime=#{aTime},aType=#{aType} where aId=#{aId} "
    })
    void saveArticle(Article article);

    @Update({
            "update article set aState='false',aTime=#{aTime} where aId=#{aId}"
    })
    void deleteArticle(@Param("aId") int aId,@Param("aTime") long aTime);

    @Insert({
            "insert into article (aText,aHtml,aUserId,aTime,aType,aTitle) values (#{aText},#{aHtml},#{aUserId},#{aTime},#{aType},#{aTitle})"
    })
    void publishArticle(Article article);


    @Select({
            "select from_unixtime(aTime,'%Y-%m-%d') as 'time',count(from_unixtime(aTime,'%Y-%m-%d')) as 'count' from article where aState='true' GROUP BY from_unixtime(aTime,'%Y-%m-%d')"
    })
    List<Map> getTimeCount();

    @Select({
            "select aType, count(*) from article where aState='true' and aType!=''  GROUP BY aType"
    })
    List<Map> getAllType();
}
