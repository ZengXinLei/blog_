package com.example.mapping;

import com.example.api.LogApi;
import com.example.enity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zxl
 * @Time: 2020/06/19 13:53:05
 * @system: ASUS
 **/
@Component
public interface LogMapping {



    @Insert({
            "insert into log (lTime,lLevel,lClass,lAction,lMethod) values (#{lTime},#{lLevel},#{lClass},#{lAction},#{lMethod})"
    })
    void insertLog(Log log);


    @Select({
            "select * from log order by lTime DESC limit #{start},#{offset}"
    })
    List<Log> getLogApis(@Param("start") int start,@Param("offset") int offset);

    @Select({
            "select count(*) from log"
    })
    int getLogApiPage();
}
