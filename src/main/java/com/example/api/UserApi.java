package com.example.api;

import com.example.enity.User;
import com.example.mapping.UserMapping;
import com.example.util.DealUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: zxl
 * @Time: 2020/05/27 15:01:30
 * @system: ASUS
 **/
@RestController
//@CrossOrigin
@Api(tags = "管理员相关接口",description = "获取管理员相关的restful API")
public class UserApi {
    @Autowired
    private UserMapping userMapping;

    @PostMapping("/login")
//    @CrossOrigin
    @ApiOperation("登录")
    public User login(
            @RequestParam("uName") @ApiParam("用户名") String uName,
            @RequestParam("uPassword") @ApiParam("密码") String uPassword,
            HttpSession httpSession){
        User user=userMapping.verifyTheLogin(uName,uPassword);
        if(user==null)
            return null;
        httpSession.setAttribute("user",user);
        return DealUser.encodeUser(user);

    }


    @GetMapping("/getInfo")
    @ApiOperation("获取加密过的管理员信息")
    public User getInfo(HttpSession httpSession){
        return DealUser.encodeUser((User) httpSession.getAttribute("user"));
    }


    @PostMapping("/logOut")
    @ApiOperation("退出登录")
    public void logOut(HttpSession httpSession){
        httpSession.setAttribute("user",null);
    }


    @PostMapping("/cookieLogin")
    @ApiOperation("使用cookie登录")
    public User cookieLogin(@RequestBody User user,HttpSession httpSession){
        User u=DealUser.decoderUser(user);
        User user1=userMapping.verifyTheLogin(u.getUName(),u.getUPassword());
        if(user1==null){
            return null;
        }
        httpSession.setAttribute("user",user1);
        return DealUser.encodeUser(user1);
    }
}
