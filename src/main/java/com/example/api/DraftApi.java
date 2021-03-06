package com.example.api;

import com.alibaba.fastjson.JSON;
import com.example.enity.Draft;
import com.example.enity.User;
import com.example.mapping.DraftMapping;
import com.example.util.DealUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author: zxl
 * @Time: 2020/05/28 21:06:42
 * @system: ASUS
 **/
@RestController
@Api(tags = "草稿相关接口",description = "获取草稿相关RESTFUL API")
public class DraftApi {

    @Autowired
    private DraftMapping draftMapping;

    private String uuid = "0";

    @PostMapping("/saveDrafts")
    @ApiOperation("保存草稿，有权限限制")
    public Map<String, Object> saveDrafts(@RequestBody @ApiParam("草稿对象") Map map, HttpSession httpSession) {

        Draft draft = JSON.parseObject(JSON.toJSONString(map), Draft.class);
        HashMap<String, Object> result = new HashMap<>();

        if (draft.getDtitle() != null && (draft.getDtitle().length() < 5 || draft.getDtitle().length() > 100)) {
            result.put("type", "error");
            result.put("msg", "标题必须在5-100个字符");
        } else if (draft.getDtype() != null && draft.getDtype().length() > 280) {
            result.put("type", "error");
            result.put("msg", "标签过多");
        } else if (((User) httpSession.getAttribute("user")) == null) {
            result.put("type", "error");
            result.put("msg", "请先登录");
        } else if (draft.getDid() != null) {
            draft.setDtime(new Date().getTime()/1000);
            draft.setDuserId(((User) httpSession.getAttribute("user")).getUId());
            draftMapping.updateDraft(draft);
            result.put("type", "success");
            result.put("msg", "保存成功");
            result.put("draft", draft);
        } else {


            draft.setDid(UUID.randomUUID().toString());
            draft.setDtime(new Date().getTime()/1000);
            draft.setDuserId(((User) httpSession.getAttribute("user")).getUId());
            draftMapping.insertDraft(draft);

            result.put("type", "success");
            result.put("msg", "保存成功");
            result.put("draft", draft);
        }


        return result;
    }

    @PostMapping("/getDraftPages")
    @ApiOperation("获取草稿的总页数")
    public int getDraftPages(){
        return draftMapping.getDraftPages();
    }

    @PostMapping("/getDraftList")
    @ApiOperation("分页获取草稿")
    public List<Draft> getDraftList(
            @RequestParam("start") @ApiParam("开始的地方") int start,
            @RequestParam("offset") @ApiParam("获取的数据量") int offset) {
        List<Draft> draftList = draftMapping.getDraftList(start, offset);
        for (Draft d :
                draftList) {
            d.setUser(DealUser.encodeUser(d.getUser()));
        }
        return draftList;
    }

    @PostMapping("/deleteDraft")
    @ApiOperation("删除草稿，有权限限制")
    public Map<String, String> deleteDraft(
            @RequestParam("did") @ApiParam("草稿主键") String did ,
            HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Map<String, String> map = new HashMap<String, String>();
        if (user == null) {
            map.put("type", "error");
            map.put("msg", "删除失败，请重新登录");
            return map;
        } else if (user.getULevel() < 9) {
            map.put("type", "error");
            map.put("msg", "删除失败，权限不够");
            return map;
        } else {
            map.put("type", "success");
            map.put("msg", "删除成功!!");
            draftMapping.deleteDratf(did,new Date().getTime()/1000);
            return map;
        }

    }


    @PostMapping("/getDraftById")
    @ApiOperation("根据草稿主键获取草稿，有权限限制")
    public Map<String, Object> getDraftById(
            @RequestParam("did") @ApiParam("草稿主键") String did,
            HttpSession httpSession) {
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            map.put("type", "error");
            map.put("msg", "未登录");
        } else if (user.getULevel() < 9) {
            map.put("type", "error");
            map.put("msg", "权限等级不够");
        } else {

            Draft draft = draftMapping.getDraftById(did);
            if (draft == null) {
                map.put("type", "error");
                map.put("msg", "获取失败");
            } else {
                map.put("type", "success");
                map.put("msg", "获取成功");
                map.put("draft", draft);
            }

        }
        return map;
    }


    @PostMapping("/getTimeCount")
    @ApiOperation("获取草稿的时间线，有权限要求")
    public List<Map> getTimeCount(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if(user==null){
            return null;
        }
        else if(user.getULevel()<9){
            return null;
        }
        else {
            return draftMapping.getTimeCount();
        }
    }
}
