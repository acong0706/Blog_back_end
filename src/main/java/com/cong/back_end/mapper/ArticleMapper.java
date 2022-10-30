package com.cong.back_end.mapper;

import com.cong.back_end.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-24 下午 05:59
 * @Project_name back_end
 */
@Mapper
@Repository
public interface ArticleMapper {
    
    // 新增文章
    int insertArticle(Article article);
    
    //获得所有文章基础信息
    List<Article> getAllArticles();
    
    //获得单个文章全部信息
    Article getArticle(int id);
    
    // 增加访问量
    int addViews(int id);
    
    // 更新文章内容
    int updateArticle(Article article);
    
    // 热门top5（根据访问量）
    List<Article> getTop5();
    
    // 文章归档展示
    List<Article> getArchive();
    
    // 展示指定归档位置的文章
    List<Article> getArticlesByArchive(Article article);
}
