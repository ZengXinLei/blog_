package com.example.mapping;

import com.example.enity.Draft;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: zxl
 * @Time: 2020/05/27 14:06:17
 * @system: ASUS
 **/
@Component
public interface DraftMapping {


    @Insert({
            "insert into draft (did,dtitle,dtext,dhtml,dtime,duserId,dtype) values (#{did},#{dtitle},#{dtext},#{dhtml},#{dtime},#{duserId},#{dtype})"
    })
    void insertDraft(Draft draft);


    @Update({
            "update draft set dtext=#{dtext},dtitle=#{dtitle},dhtml=#{dhtml},dtime=#{dtime} where did=#{did}"
    })
    void updateDraft(Draft draft);



    @Select({
            "select * from draft where dstate='true' limit #{start},#{offset}"
    })
    @Results({
            @Result(property = "duserId",column = "duserid"),
            @Result(property = "user",column = "duserid", one = @One(select = "com.example.mapping.UserMapping.getUserById"))
    })
    List<Draft> getDraftList(@Param("start") int start,@Param("offset") int offset);

    @Select({
            "select count(*) from draft"
    })
    int getDraftPages();

    @Select({
            "select * from draft where did=#{did} and dstate='true'"
    })
    Draft getDraftById(@Param("did") String did);

    @Update({
            "update draft set dstate='false',dtime=#{dtime} where did=#{did}"
    })
    void deleteDratf(@Param("did") String did,@Param("dtime") long dtime);



    @Select({
            "select from_unixtime(dtime,'%Y-%m-%d') as 'time',count(from_unixtime(dtime,'%Y-%m-%d')) as 'count' from draft where dstate='true' GROUP BY from_unixtime(dtime,'%Y-%m-%d');"
    })
    List<Map> getTimeCount();


}

