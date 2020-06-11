package com.example.api;

import com.example.enity.Give;
import com.example.mapping.GiveMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: zxl
 * @Time: 2020/06/05 17:12:16
 * @system: ASUS
 **/
@RestController
@Api(tags = "点赞相关接口",description = "获取点赞相关的Restful API")
public class GiveApi {
    @Autowired
    GiveMapping giveMapping;


    @PostMapping("/like")
    @ApiOperation("点赞或取消点赞")
    public void like(@RequestBody @ApiParam("点赞实体类") Give give){
        if(giveMapping.isLike(give)==null){
            give.setGTime(new Date().getTime()/1000);
            giveMapping.like(give);
        }
        else {
            giveMapping.unLike(give);
        }
    }

    @PostMapping("/isLike")
    @ApiOperation("是否点赞了")
    public Give isLike(@RequestBody @ApiParam("点赞实体类") Give give){
        return giveMapping.isLike(give);
    }




}
