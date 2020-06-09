package com.example.api;

import com.example.enity.Article;
import com.example.enity.User;
import com.example.mapping.ArticleMapping;
import com.example.util.DealUser;
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
public class ArticleApi {
    @Autowired
    private ArticleMapping articleMapping;

    @PostMapping("/getArticleList")
    public List<Article> getArticles(@RequestParam("start") int start,@RequestParam("offset") int offset,@RequestParam("aType") String aType){
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
    public int getArticlePages(@RequestParam("aType") String aType){
        if("".equals(aType)){
            return articleMapping.getArticlePages();
        }
        return articleMapping.getArticlePagesByTag("%"+aType+"%");

    }


    @PostMapping("/getArticleById")
    public Article getArticleById(@RequestParam("aId") int aId){
        Article article = articleMapping.getArticleById(aId);
        if(article==null){
            return null;
        }
        article.setUser(DealUser.encodeUser(article.getUser()));
        return article;
    }

    @PostMapping("/deleteArticle")
    public Map<String,String> deleteArticle(@RequestParam("aId") int aId,HttpSession httpSession){
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
    public Map saveArticle(@RequestBody Article article,HttpSession httpSession){
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
    public Map<String,String> publishArticle(@RequestBody Article article, HttpSession httpSession){
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
