package com.example.api;

import com.alibaba.fastjson.JSON;
import com.example.enity.Draft;
import com.example.enity.User;
import com.example.mapping.DraftMapping;
import com.example.util.DealUser;
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
public class DraftApi {

    @Autowired
    private DraftMapping draftMapping;

    private String uuid = "0";

    @PostMapping("/saveDrafts")
    public Map<String, Object> saveDrafts(@RequestBody Map map, HttpSession httpSession) {

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
    public int getDraftPages(){
        return draftMapping.getDraftPages();
    }

    @PostMapping("/getDraftList")
    public List<Draft> getDraftList(@RequestParam("start") int start, @RequestParam("offset") int offset) {
        List<Draft> draftList = draftMapping.getDraftList(start, offset);
        for (Draft d :
                draftList) {
            d.setUser(DealUser.encodeUser(d.getUser()));
        }
        return draftList;
    }

    @PostMapping("/deleteDraft")
    public Map<String, String> deleteDraft(@RequestParam("did") String did, HttpSession httpSession) {
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
    public Map<String, Object> getDraftById(@RequestParam("did") String did, HttpSession httpSession) {
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
