package com.example.mapping;

import com.example.enity.Looked;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @Author: zxl
 * @Time: 2020/06/05 15:58:58
 * @system: ASUS
 **/
@Component
public interface LookedMapping {

    @Insert({
            "insert into looked (lArticleId,lTouristId,lTime) values (#{lArticleId},#{lTouristId},#{lTime})"
    })
    void readArticle(Looked looked);


    @Select({
            "select * from looked where lArticleId=#{lArticleId} and lTouristId=#{lTouristId}"
    })
    Looked isRead(Looked looked);

    @Update({
            "update looked set lTime=#{lTime} where lArticleId=#{lArticleId} and lTouristId=#{lTouristId}"
    })
    void updateRead(Looked looked);

    @Select({
            "select count(*) from looked where lArticleId=#{lArticleId}"
    })
    int readCount(@Param("lArticleId") int id);
}
