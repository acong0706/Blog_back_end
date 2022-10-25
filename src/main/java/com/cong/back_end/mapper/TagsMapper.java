package com.cong.back_end.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-25 下午 04:20
 * @Project_name back_end
 */
@Mapper
@Repository
public interface TagsMapper {
    
    // 更新旧标签的文章个数
    int updateOldTag(String tag);
    
    // 添加新标签
    int insertTag(String tag);
    
    List<String> getAllTags();
}
