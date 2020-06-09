package com.example.api;

import com.example.enity.Give;
import com.example.mapping.GiveMapping;
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
public class GiveApi {
    @Autowired
    GiveMapping giveMapping;


    @PostMapping("/like")
    public void like(@RequestBody Give give){
        if(giveMapping.isLike(give)==null){
            give.setGTime(new Date().getTime()/1000);
            giveMapping.like(give);
        }
        else {
            giveMapping.unLike(give);
        }
    }

    @PostMapping("/isLike")
    public Give isLike(@RequestBody Give give){
        return giveMapping.isLike(give);
    }




}
