package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cong
 * @Create_time 2022-10-16 下午 05:03
 * @Project_name back_end
 */
@RestController
@RequestMapping("test")
public class TestController {
    
    private JSONObject jsonObject;
    
    @PostMapping("a1a2")
    public JSONObject testA1A1(String a1, String a2) {
        System.out.println(a1 + "\t" + a2);
        jsonObject = new JSONObject();
        jsonObject.put("a1", a1);
        jsonObject.put("a2", a2);
        return jsonObject;
    }
}
