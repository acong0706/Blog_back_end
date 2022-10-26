package com.cong.back_end.service;

import com.cong.back_end.mapper.ArticleMapper;
import com.cong.back_end.mapper.TagsMapper;
import com.cong.back_end.pojo.Article;
import com.cong.back_end.pojo.PublishResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-24 下午 06:00
 * @Project_name back_end
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    
    private PublishResult result;
    private ArticleMapper articleMapper;
    private TagsMapper tagsMapper;
    
    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Autowired
    public void setTagsMapper(TagsMapper tagsMapper) { this.tagsMapper = tagsMapper; }
    
    @Override
    @Transactional
    public PublishResult publishArticle(Article article) {
        System.out.println("================");
        System.out.println(article);
        result = new PublishResult();
        try {
            // 1.存储博客
            articleMapper.insertArticle(article);
            result.setId(article.getId());
            // 2.更新旧tag数目
            String[] oldTags = article.getOldTagsArray();
            for (String oldTag : oldTags) {
                System.out.println(oldTag);
                tagsMapper.updateOldTag(oldTag);
            }
            // 3.添加新tag
            String[] newTags = article.getNewTagsArray();
            for (String newTag : newTags) {
                System.out.println(newTag);
                tagsMapper.insertTag(newTag);
            }
            System.out.println("================");
            result.setResult(true);
        } catch (Exception e) {
            result.setResult(false);
        }
        return result;
    }
    
    @Override
    public List<Article> getAllArticles() {
        return articleMapper.getAllArticles();
    }
    
    @Override
    public Article getArticle(int id) {
        return articleMapper.getArticle(id);
    }
    
    @Override
    public int addViews(int id) {
        return articleMapper.addViews(id);
    }
    
    @Override
    @Transactional
    public boolean editArticle(Article article) {
        System.out.println("================");
        System.out.println(article);
        result = new PublishResult();
        try {
            // 1.存储博客
            articleMapper.updateArticle(article);
            // 2.对移除的tag进行数量减一
            String[] removeTags = article.getRemoveTagsArray();
            for (String removeTag : removeTags) {
                System.out.println(removeTag);
                tagsMapper.updateRemoveTag(removeTag);
            }
            // 3.对新增的旧标签进行数量加一
            String[] beforeTags = article.getBeforeTagsArray();
            for (String beforeTag : beforeTags) {
                System.out.println(beforeTag);
                tagsMapper.updateOldTag(beforeTag);
            }
            // 4.对新增的tag进行添加
            String[] newTags = article.getNewTagsArray();
            for (String newTag : newTags) {
                System.out.println(newTag);
                tagsMapper.insertTag(newTag);
            }
            System.out.println("================");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
