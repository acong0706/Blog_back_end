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
    
    List<Article> getAllArticles();
    
    Article getArticle(int id);
    
    int addViews(int id);
    
    int updateArticle(Article article);
}
