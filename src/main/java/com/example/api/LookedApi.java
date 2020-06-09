package com.example.api;

import com.example.enity.Looked;
import com.example.mapping.LookedMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zxl
 * @Time: 2020/06/05 15:59:11
 * @system: ASUS
 **/
@RestController
public class LookedApi {
    @Autowired
    LookedMapping lookedMapping;


    @PostMapping("/readArticle")
    public void readArticle(@RequestBody Looked looked){
        //如果没看过，插入数据表
        if(lookedMapping.isRead(looked)==null)
            lookedMapping.readArticle(looked);
        //看过，更新时间
        else
            lookedMapping.updateRead(looked);
    }
}
