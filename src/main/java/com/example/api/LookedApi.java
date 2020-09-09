package com.example.api;

import com.example.enity.Looked;
import com.example.enity.User;
import com.example.mapping.LookedMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: zxl
 * @Time: 2020/06/05 15:59:11
 * @system: ASUS
 **/
@RestController
@Api(tags = "浏览相关的接口",description = "获取点赞相关的restful API")
public class LookedApi {
    @Autowired
    LookedMapping lookedMapping;


    @PostMapping("/readArticle")
    @ApiOperation("阅读这篇文章")
    public void readArticle(@RequestBody @ApiParam("阅读实体类") Looked looked){
        //如果没看过，插入数据表
        if(lookedMapping.isRead(looked)==null)
            lookedMapping.readArticle(looked);
        //看过，更新时间
        else
            lookedMapping.updateRead(looked);
    }


    @PostMapping("/getLookedTimeCount")
    @ApiOperation("获取浏览的时间线")
    public List<Map> getLookedTimeCount(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if(user==null){
            return null;
        }
        else if(user.getULevel()<9){
            return null;
        }
        else {
            return lookedMapping.getLookedTimeCount();
        }
    }
}
