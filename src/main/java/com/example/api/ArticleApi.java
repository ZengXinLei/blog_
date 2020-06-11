package com.example.api;

import com.example.enity.Article;
import com.example.enity.User;
import com.example.mapping.ArticleMapping;
import com.example.util.DealUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @Author: zxl
 * @Time: 2020/05/27 14:09:02
 * @system: ASUS
 **/
@RestController
@Api(tags = "文章相关接口",description = "提供文章相关的Rest API")
public class ArticleApi {
    @Autowired
    private ArticleMapping articleMapping;

    @ApiOperation(value = "分页获取文章列表",notes = "如果文章种类为空，则按默认顺序查找，如果不为空，则按文章种类进行分页查找")
    @PostMapping("/getArticleList")
    public List<Article> getArticles(@RequestParam("start") @ApiParam("开始") int start,
                                     @RequestParam("offset") @ApiParam("分页的页数") int offset,
                                     @RequestParam("aType") @ApiParam("文章的种类") String aType){
        List<Article> articleList=null;
        if(!"".equals(aType)){
            articleList=articleMapping.getArticleListByTag(start, offset,"%"+aType+"%");
        }
        else {
            articleList = articleMapping.getArticleList(start, offset);
        }

        for (Article a :
                articleList) {
            a.setUser(DealUser.encodeUser(a.getUser()));
        }

        return articleList;
    }

    @PostMapping("/getArticlePages")
    @ApiOperation(value = "获取文章总数",notes = "如果种类不为空，则获取该种类的所有文章的总数")
    public int getArticlePages(
            @RequestParam("aType") @ApiParam("文章种类") String aType
    ){
        if("".equals(aType)){
            return articleMapping.getArticlePages();
        }
        return articleMapping.getArticlePagesByTag("%"+aType+"%");

    }

    @ApiOperation(value = "通过文章主键获取文章")
    @PostMapping("/getArticleById")
    public Article getArticleById(
            @RequestParam("aId") @ApiParam("文章主键") int aId
    ){
        Article article = articleMapping.getArticleById(aId);
        if(article==null){
            return null;
        }
        article.setUser(DealUser.encodeUser(article.getUser()));
        return article;
    }

    @PostMapping("/deleteArticle")
    @ApiOperation("通过文章主键删除文章，有权限要求")
    public Map<String,String> deleteArticle(
            @RequestParam("aId") @ApiParam("文章主键") int aId,
            @ApiParam("session数据") HttpSession httpSession
    ){
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
            articleMapping.deleteArticle(aId,new Date().getTime()/1000);
            return map;
        }
    }

    @PostMapping("saveArticle")
    @ApiOperation("保存文章")
    public Map saveArticle(@RequestBody @ApiParam("文章对象") Article article,@ApiParam("session数据") HttpSession httpSession){
        HashMap<String, String> map = new HashMap<>();
        User user= (User) httpSession.getAttribute("user");
        if(user==null){
            map.put("type","error");
            map.put("msg","请重新登录");

        }
        else if (user.getULevel()<9){
            map.put("type","error");
            map.put("msg","权限不够");
        }
        else {
            map.put("type","success");
            map.put("msg","发布成功");
            article.setAUserId(user.getUId());
            article.setATime(new Date().getTime()/1000);
            articleMapping.saveArticle(article);

        }
        return map;
    }

    @PostMapping("/publishArticle")
    @ApiOperation("发布文章")
    public Map<String,String> publishArticle(@RequestBody @ApiParam("文章对象") Article article,@ApiParam("session数据")  HttpSession httpSession){
        HashMap<String, String> map = new HashMap<>();
        User user = (User) httpSession.getAttribute("user");
        if(user==null){
            map.put("type","error");
            map.put("msg","请重新登录");
        }
        else if (user.getULevel()<9){
            map.put("type","error");
            map.put("msg","权限等级不够");
        }
        else if(article.getATitle()==null||article.getATitle().length()<5||article.getATitle().length()>100){
            map.put("type","error");
            map.put("msg","标个字符之间题必须在5——100");
        }
        else if(article.getAType()!=null&&article.getAType().length()>300){
            map.put("type","error");
            map.put("msg","标签太长");
        }
        else {
            map.put("type","success");
            map.put("msg","发布成功");
            article.setAUserId(user.getUId());
            article.setATime(new Date().getTime()/1000);
            articleMapping.publishArticle(article);
        }
        return map;
    }


    @PostMapping("/getAllType")
    @ApiOperation("获取文章的所有种类")
    public Set<String> getAllType(){
        List<Map> types = articleMapping.getAllType();
        Set set = new HashSet<String>();
        for (Map map :
                types) {
            String aType = (String) map.get("aType");
            set.addAll(Arrays.asList(aType.split("\\|")));
        }
        return set;

    }

    @PostMapping("/getArticleTimeCount")
    @ApiOperation("获取文章的发布时间线,有权限验证")
    public List<Map> getArticleTimeCount(HttpSession httpSession){
        User user= (User) httpSession.getAttribute("user");
        if(user==null){
            return null;
        }
        if(user.getULevel()<9){
            return null;
        }
        return articleMapping.getTimeCount();
    }
}
