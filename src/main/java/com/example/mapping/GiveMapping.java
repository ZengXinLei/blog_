package com.example.mapping;

import com.example.enity.Give;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: zxl
 * @Time: 2020/06/05 17:12:00
 * @system: ASUS
 **/
@Component
public interface GiveMapping {

    @Insert({
            "insert into give (gArticleId,gTouristId,gTime) values (#{gArticleId},#{gTouristId},#{gTime})"
    })
    void like(Give give);
    @Select({
            "select * from give where gArticleId=#{gArticleId} and gTouristId=#{gTouristId}"
    })
    Give isLike(Give give);

    @Delete({
            "delete from give where gArticleId=#{gArticleId} and gTouristId=#{gTouristId}"
    })
    void unLike(Give give);

    @Select({
            "select count(*) from give where gArticleId=#{gArticleId}"
    })
    int likeCount(@Param("gArticleId") int id);
}
