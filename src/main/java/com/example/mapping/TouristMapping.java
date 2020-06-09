package com.example.mapping;

import com.example.enity.Tourist;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: zxl
 * @Time: 2020/06/05 14:58:15
 * @system: ASUS
 **/
@Component
public interface TouristMapping {


    @Insert({
            "insert into tourist (tId,tTime,tIp,tName,tPhoto) values (#{tId},#{tTime},#{tIp},#{tName},#{tPhoto})"
    })
    void signTourist(Tourist tourist);

    @Select({
            "select count(*) from tourist"
    })
    int touristCount();

    @Select({
            "select * from tourist where tId=#{tId}"
    })
    Tourist getTouristById(@Param("tId") String tId);



}
