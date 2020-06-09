package com.example.mapping;

import com.example.enity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zxl
 * @Time: 2020/06/06 21:50:47
 * @system: ASUS
 **/
@Component
public interface CommentMapping {


    @Insert({
            "call addComment(#{cId},#{cText},#{cTouristId},#{cArticleId},#{cReviewId},#{cTime})"
    })
    void addComment(Comment comment);


    @Select({
            "select * from comment where cArticleId=#{cArticleId} and cState='true' order by cTime ASC limit #{start},#{offset}"
    })
    @Results({
            @Result(property = "cTouristId",column = "cTouristId"),
            @Result(property = "cReviewId",column = "cReviewId"),

            @Result(property = "comment",column = "cReviewId",one = @One(select ="com.example.mapping.CommentMapping.getCommentById" )),
            @Result(property = "tourist",column = "cTouristId",one = @One(select = "com.example.mapping.TouristMapping.getTouristById"))
    })
    List<Comment> getComments(@Param("cArticleId") int aId,@Param("start") int start,@Param("offset") int offset);
    @Select({
            "select * from comment where cState='true' order by cTime ASC limit #{start},#{offset}"
    })
    @Results({
            @Result(property = "cTouristId",column = "cTouristId"),
            @Result(property = "cReviewId",column = "cReviewId"),
            @Result(property = "cArticleId",column = "cArticleId"),
            @Result(property = "article",column = "cArticleId",one = @One(select = "com.example.mapping.ArticleMapping.getArticleById")),
            @Result(property = "comment",column = "cReviewId",one = @One(select ="com.example.mapping.CommentMapping.getCommentById" )),
            @Result(property = "tourist",column = "cTouristId",one = @One(select = "com.example.mapping.TouristMapping.getTouristById"))
    })
    List<Comment> getAllComments(@Param("start") int start,@Param("offset") int offset);



    @Select({
            "select * from comment where cId=#{cReviewId} and cState='true'"
    })
    @Results({
            @Result(property = "cTouristId",column = "cTouristId"),
            @Result(property = "tourist",column = "cTouristId",one = @One(select = "com.example.mapping.TouristMapping.getTouristById"))
    })
    Comment getCommentById(@Param("cReviewId") String cId);


    @Select({
            "select count(*) from comment where cArticleId=#{cArticleId} and cState='true'"
    })
    int getCommentCount(@Param("cArticleId") int id);


    @Select({
            "select count(*) from comment where cState='true'"
    })
    int getAllCommentsPage();


    @Update({
            "update comment set cState='false' where cId=#{cId}"
    })
    void deleteComment(@Param("cId") String cId);
}
