package com.example.api;

import com.example.enity.Tourist;
import com.example.enity.User;
import com.example.mapping.TouristMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: zxl
 * @Time: 2020/06/05 14:58:24
 * @system: ASUS
 **/
@RestController
@Api(tags = "游客相关接口",description = "获取游客相关的restful API")
public class TouristApi {

    @Autowired
    TouristMapping touristMapping;
    @PostMapping("/signTourist")
    @ApiOperation("注册成为游客")
    public Tourist signTourist(){
        Tourist tourist=new Tourist();
        tourist.setTId(UUID.randomUUID().toString());
        tourist.setTTime(new Date().getTime()/1000);
        int ran=(int)(Math.random()*100);

        tourist.setTPhoto("head_"+ran+".jpg");
        tourist.setTName("游客_"+(touristMapping.touristCount()+1));
        touristMapping.signTourist(tourist);
        return tourist;
    }

    @PostMapping("/getTouristTimeCount")
    @ApiOperation("获取游客注册时间线")
    public List<Map> getTouristTimeCount(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if(user==null){
            return null;
        }
        else if(user.getULevel()<9){
            return null;
        }else {
            return touristMapping.getTouristTimeCount();
        }
    }
}
