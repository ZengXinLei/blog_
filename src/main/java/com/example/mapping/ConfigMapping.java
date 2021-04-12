package com.example.mapping;

import com.example.enity.Config;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfigMapping {

    @Select({
            "select * from config where name=#{name}"
    })
    Config getConfig(@Param("name") String name);


    @Select({
            "select * from config limit #{page},#{limit} "
    })
    List<Config> list(@Param("page") int page,@Param("limit") int limit);


    @Select({
            "select count(*) from config"
    })
    int getConfigCount();

    @Insert({
            "insert into config (name,value,time) values (#{name},#{value},#{time})"
    })
    int setConfig(Config config);

    @Update({
            "update config set value=#{value},time=#{time} where name=#{name}"
    })
    int updataConfig(Config config);

    @Delete({
            "delete from config where name=#{name}"
    })
    void deleteConfig(@Param("name") String name);
}
