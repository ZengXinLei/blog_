package com.example.api;

import com.example.enity.User;
import com.example.mapping.UserMapping;
import com.example.util.DealUser;
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
public class UserApi {
    @Autowired
    private UserMapping userMapping;

    @PostMapping("/login")
//    @CrossOrigin
    public User login(@RequestParam("uName") String uName, @RequestParam("uPassword") String uPassword, HttpSession httpSession){
        User user=userMapping.verifyTheLogin(uName,uPassword);
        if(user==null)
            return null;
        httpSession.setAttribute("user",user);
        return DealUser.encodeUser(user);

    }


    @GetMapping("/getInfo")
    public User getInfo(HttpSession httpSession){
        return DealUser.encodeUser((User) httpSession.getAttribute("user"));
    }


    @PostMapping("/logOut")
    public void logOut(HttpSession httpSession){
        httpSession.setAttribute("user",null);
    }


    @PostMapping("/cookieLogin")
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
