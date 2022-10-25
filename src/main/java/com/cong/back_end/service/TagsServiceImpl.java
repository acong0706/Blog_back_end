package com.cong.back_end.service;

import com.cong.back_end.mapper.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-25 下午 04:26
 * @Project_name back_end
 */
@Service
public class TagsServiceImpl implements TagsService {
    
    private TagsMapper tagsMapper;
    
    @Autowired
    public void setTagsMapper(TagsMapper tagsMapper) { this.tagsMapper = tagsMapper; }
    
    @Override
    public List<String> getAllTags() {
        return tagsMapper.getAllTags();
    }
}
