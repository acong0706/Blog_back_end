package com.cong.back_end.service;

import com.cong.back_end.pojo.Article;
import com.cong.back_end.pojo.PublishResult;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-24 下午 05:59
 * @Project_name back_end
 */
public interface ArticleService {
    
    PublishResult publishArticle(Article article);
    
    List<Article> getAllArticles();
    
    Article getArticle(int id);
    
    int addViews(int id);
    
    boolean editArticle(Article article);
}
