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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        System.out.println("=============");
        System.out.println(article);
        System.out.println("=============");
        article = dataProcess(article);  // 内容无误
        System.out.println(article);
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
        // System.out.println(article);
        jsonObject.put("article", article);
        return jsonObject;
    }
    
    @PostMapping("/addViews")
    public JSONObject addViews(Article article) {
        jsonObject = new JSONObject();
        int result = articleService.addViews(article.getId());
        if (result > 0) {
            jsonObject.put("result", true);
        } else {
            jsonObject.put("result", false);
        }
        return jsonObject;
    }
    
    @PostMapping("/edit")
    public JSONObject edit(Article article) {
        System.out.println(article);
        // 内容无误
        article = dataProcess(article);
        PublishResult result = new PublishResult();
        result.setId(article.getId());
        result.setResult(
                articleService.editArticle(article)
        );
        jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }
    
    @GetMapping("/top5_archive")
    public JSONObject top5_archive() {
        jsonObject = new JSONObject();
        List<Article> articles1 = articleService.getTop5();
        jsonObject.put("articles1", articles1);
        List<Article> articles2 = articleService.getArchive();
        jsonObject.put("articles2", articles2);
        return jsonObject;
    }
    
    @PostMapping("/getArticlesByArchive")
    public JSONObject getArticlesByArchive(Article article) {
        jsonObject = new JSONObject();
        List<Article> articles = articleService.getArticlesByArchive(article);
        jsonObject.put("articles", articles);
        return jsonObject;
    }
    
    private Article dataProcess(Article article) {
        // 效果举例
        // 处理前：[0=springboot&1=docker]
        // 处理后：[springboot, docker] 和 springboot,docker
        String[] tagsString1 = article.getOldTags().split("&");
        if (tagsString1.length == 0 || Objects.equals(tagsString1[0], "")) {
            article.setOldTags("");
            article.setOldTagsArray(new String[0]);
        } else {
            article.setOldTags(
                    LabelChange.labelArrayToString(tagsString1)
            );
            article.setOldTagsArray(
                    LabelChange.labelStringToArray(article.getOldTags())
            );
        }
        String[] tagsString2 = article.getNewTags().split("&");
        if (tagsString2.length == 0 || Objects.equals(tagsString2[0], "")) {
            article.setNewTags("");
            article.setNewTagsArray(new String[0]);
        } else {
            article.setNewTags(
                    LabelChange.labelArrayToString(tagsString2)
            );
            article.setNewTagsArray(
                    LabelChange.labelStringToArray(article.getNewTags())
            );
        }
        String[] tagsString3 = article.getRemoveTags().split("&");
        if (tagsString3.length == 0 || Objects.equals(tagsString3[0], "")) {
            article.setRemoveTags("");
            article.setRemoveTagsArray(new String[0]);
        } else {
            article.setRemoveTags(
                    LabelChange.labelArrayToString(tagsString3)
            );
            article.setRemoveTagsArray(
                    LabelChange.labelStringToArray(article.getRemoveTags())
            );
        }
        String[] tagsString4 = article.getBeforeTags().split("&");
        if (tagsString4.length == 0 || Objects.equals(tagsString4[0], "")) {
            article.setBeforeTags("");
            article.setBeforeTagsArray(new String[0]);
        } else {
            article.setBeforeTags(
                    LabelChange.labelArrayToString(tagsString4)
            );
            article.setBeforeTagsArray(
                    LabelChange.labelStringToArray(article.getBeforeTags())
            );
        }
        if (Objects.equals(article.getOldTags(), "")) {
            article.setTags(article.getNewTags());
        } else {
            if (Objects.equals(article.getNewTags(), "")) {
                article.setTags(article.getOldTags());
            } else {
                article.setTags(article.getOldTags() + ',' + article.getNewTags());
            }
        }
        // 通过发布时间更新yearMonth
        article.setYearMonth(article.getPublishDate().substring(0, 7));
        return article;
    }
}
