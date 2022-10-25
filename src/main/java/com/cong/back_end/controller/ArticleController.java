package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.pojo.Article;
import com.cong.back_end.pojo.PublishResult;
import com.cong.back_end.service.ArticleService;
import com.cong.back_end.util.LabelChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-21 下午 07:28
 * @Project_name back_end
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    
    private JSONObject jsonObject;
    private ArticleService articleService;
    
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
    
    @PostMapping("/publish")
    public JSONObject publish(Article article) {
        // 内容无误
        article = dataProcess(article);
        jsonObject = new JSONObject();
        // true: 发布成功
        // false: 发布失败
        PublishResult result = articleService.publishArticle(article);
        jsonObject.put("result", result.isResult());
        if (result.isResult()) {
            jsonObject.put("articleId", result.getId());
            jsonObject.put("msg", "发布成功");
        } else {
            jsonObject.put("msg", "发布失败");
        }
        return jsonObject;
    }
    
    @GetMapping("/getArticles")
    public JSONObject getArticles() {
        jsonObject = new JSONObject();
        List<Article> resultArticles = articleService.getAllArticles();
        jsonObject.put("articles", resultArticles);
        return jsonObject;
    }
    
    @PostMapping("/getArticle")
    public JSONObject getArticle(Article article) {
        jsonObject = new JSONObject();
        article = articleService.getArticle(article.getId());
        System.out.println(article);
        jsonObject.put("article", article);
        return jsonObject;
    }
    
    private Article dataProcess(Article article) {
        // 效果举例
        // 处理前：[0=springboot&1=docker]
        // 处理后：[springboot, docker] 和 springboot,docker
        String[] tagsString1 = article.getOldTags().split("&");
        article.setOldTags(
                LabelChange.labelArrayToString(tagsString1)
        );
        String[] tagsString2 = article.getNewTags().split("&");
        article.setNewTags(
                LabelChange.labelArrayToString(tagsString2)
        );
        article.setOldTagsArray(
                LabelChange.labelStringToArray(article.getOldTags())
        );
        article.setNewTagsArray(
                LabelChange.labelStringToArray(article.getNewTags())
        );
        article.setTags(article.getOldTags() + ',' + article.getNewTags());
        return article;
    }
}
