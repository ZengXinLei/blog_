package com.example.api;

import com.example.enity.Log;
import com.example.mapping.LogMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zxl
 * @Time: 2020/06/19 14:15:25
 * @system: ASUS
 **/
@RestController
public class LogApi {
    @Autowired
    LogMapping logMapping;

    @PostMapping("/getLogApis")
    public List<Log> getLogApis(@RequestParam("start") int start,@RequestParam("offset") int offset){
        return logMapping.getLogApis(start,offset);
    }
    @PostMapping("/getLogApiPage")
    public int getLogApiPage(){
        return logMapping.getLogApiPage();
    }
}
