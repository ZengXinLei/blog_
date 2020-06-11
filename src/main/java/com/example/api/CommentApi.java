package com.example.api;

import com.example.enity.Comment;
import com.example.enity.User;
import com.example.mapping.CommentMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "评论相关接口",description = "获取评论相关的Rest Api")
public class CommentApi {

    @Autowired
    CommentMapping commentMapping;

    @PostMapping("/addComment")
    @ApiOperation("添加评论")
    public Comment addComment(@RequestBody @ApiParam("评论对象") Comment comment){
        comment.setCId(UUID.randomUUID().toString());
        commentMapping.addComment(comment);
        return comment;
    }

    @PostMapping("/getComments")
    @ApiOperation("按文章对评论进行分页获取，如果分页为0，则获取全部")
    public List<Comment> getComments(

            @RequestParam("aId") @ApiParam("文章主键") int aId,
            @RequestParam("start") @ApiParam("开始分页的页数") int start,
            @RequestParam("offset") @ApiParam("一次获取多少页") int offset){
        if(offset==0){
            offset=commentMapping.getCommentCount(aId);
        }

        return commentMapping.getComments(aId,start,offset);
    }


    @PostMapping("/getCommentCount")
    @ApiOperation("按文章获取评论的数量")
    public int getCommentCount(@RequestParam("aId") @ApiParam("文章主键") int aId){
        return commentMapping.getCommentCount(aId);
    }


    @PostMapping("/getAllComments")
    @ApiOperation("分页获取所有评论，需要权限")
    public List<Comment> getAllComments(
            @RequestParam("start") int start,
            @RequestParam("offset") int offset,
            HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        if(user==null||user.getULevel()<9){
            return null;
        }
        return commentMapping.getAllComments(start,offset);

    }

    @PostMapping("/getAllCommentsPage")
    @ApiOperation("获取所有评论的数量")
    public int getAllCommentsPage(){
        return commentMapping.getAllCommentsPage();
    }


    @PostMapping("/deleteComment")
    @ApiOperation("通过评论主键删除评论，需要权限")
    public Map deleteComment(@RequestParam("cId") @ApiParam("评论主键") String cId, HttpSession httpSession){
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
