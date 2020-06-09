package com.example.api;

import com.example.enity.Comment;
import com.example.enity.User;
import com.example.mapping.CommentMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: zxl
 * @Time: 2020/06/06 21:50:29
 * @system: ASUS
 **/
@RestController
public class CommentApi {

    @Autowired
    CommentMapping commentMapping;

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment){
        comment.setCId(UUID.randomUUID().toString());
        commentMapping.addComment(comment);
        return comment;
    }

    @PostMapping("/getComments")
    public List<Comment> getComments(@RequestParam("aId") int aId,@RequestParam("start") int start,@RequestParam("offset") int offset){
        if(offset==0){
            offset=commentMapping.getCommentCount(aId);
        }

        return commentMapping.getComments(aId,start,offset);
    }


    @PostMapping("/getCommentCount")
    public int getCommentCount(@RequestParam("aId") int aId){
        return commentMapping.getCommentCount(aId);
    }


    @PostMapping("/getAllComments")
    public List<Comment> getAllComments(@RequestParam("start") int start, @RequestParam("offset") int offset, HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if(user==null||user.getULevel()<9){
            return null;
        }
        return commentMapping.getAllComments(start,offset);

    }

    @PostMapping("/getAllCommentsPage")
    public int getAllCommentsPage(){
        return commentMapping.getAllCommentsPage();
    }


    @PostMapping("/deleteComment")
    public Map deleteComment(@RequestParam("cId") String cId, HttpSession httpSession){
        HashMap<String, String> map = new HashMap<>();
        User user=(User)httpSession.getAttribute("user");
        if(user==null||user.getULevel()<9){
            map.put("type","error");
            map.put("msg","权限不够或者未登录");
            return map;
        }
        commentMapping.deleteComment(cId);
        map.put("type","success");
        map.put("msg","删除成功");
        return map;
    }
}
