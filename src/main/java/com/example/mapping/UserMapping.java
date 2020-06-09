package com.example.mapping;

import com.example.enity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: zxl
 * @Time: 2020/05/27 14:06:04
 * @system: ASUS
 **/
@Component
public interface UserMapping {

    @Select({
            "select * from user where uName=#{uName} and uPassword=#{uPassword}"
    })
    User verifyTheLogin(@Param("uName") String n,@Param("uPassword") String p);


    @Select({
            "select * from user where uId=#{uId}"
    })
    User getUserById(@Param("uId") long uId);
}
