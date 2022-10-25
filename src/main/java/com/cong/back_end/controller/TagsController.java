package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-25 下午 04:23
 * @Project_name back_end
 */
@RestController
@RequestMapping("/tags")
public class TagsController {
    
    private JSONObject jsonObject;
    private TagsService tagsService;
    
    @Autowired
    public void setTagsService(TagsService tagsService) {
        this.tagsService = tagsService;
    }
    
    @GetMapping("/getAllTags")
    public JSONObject getAllTags() {
        jsonObject = new JSONObject();
        List<String> resultTags = tagsService.getAllTags();
        jsonObject.put("tags", resultTags);
        return jsonObject;
    }
}
