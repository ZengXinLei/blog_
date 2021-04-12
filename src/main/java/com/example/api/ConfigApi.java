package com.example.api;

import com.example.enity.Config;
import com.example.mapping.ConfigMapping;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: com.example.api.ConfigApi
 * @description: TODO
 * @author: zxl
 * @create: 2021-04-12 14:48
 */
@RestController
@Api(tags = "网站基本设置",description = "网站基本设置的Rest Api")
public class ConfigApi {

    @Autowired
    ConfigMapping configMapping;
    @RequestMapping("/setConfig")
    public Map<String,Object> setConfig(@RequestBody Config config)
    {
        HashMap<String, Object> msg = new HashMap<>();
        if("".equals(config.getName())){
            msg.put("code",500);
            msg.put("msg","设置名称不能为空");
        }
        Config config1 = configMapping.getConfig(config.getName());
        msg.put("code",200);

        config.setTime(new Date().getTime()/1000+"");
        if(config1==null)
        {

            configMapping.setConfig(config);
        }else {
            configMapping.updataConfig(config);
        }
        msg.put("msg","设置成功");



        return msg;
    }



    @RequestMapping("/getConfigCount")
    public int getConfigCount(){

        return configMapping.getConfigCount();
    }

    @RequestMapping(value = "/getConfig",method = RequestMethod.POST)
    public List<Config> getConfig(@RequestParam("start") int page,@RequestParam("offset") int limit){

        return configMapping.list(page,limit);
    }


    @RequestMapping("/deleteConfig")
    public void deleteConfig(@RequestParam("name") String name){
        configMapping.deleteConfig(name);
    }


    @RequestMapping("/getConfigByName")
    public Config getConfigByName(@RequestParam("name") String name)
    {
        return configMapping.getConfig(name);
    }
}
