package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.pojo.AccessNum;
import com.cong.back_end.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-28 下午 09:40
 * @Project_name back_end
 */
@RestController
@RequestMapping("/access")
public class AccessController {
    
    private JSONObject jsonObject;
    private AccessService accessService;
    
    @Autowired
    public void setAccessService(AccessService accessService) {
        this.accessService = accessService;
    }
    
    @GetMapping("/getThreeAccess")
    // 获得前天、昨天、今天的访问量
    public JSONObject getThreeAccess() {
        jsonObject = new JSONObject();
        List<AccessNum> accessNums = accessService.getThreeAccess();
        jsonObject.put("accessNums", accessNums);
        return jsonObject;
    }
    
    @PostMapping("/updateAccess")
    // 更新今天的访问量
    public JSONObject updateAccess(AccessNum accessNum) {
        jsonObject = new JSONObject();
        int result = accessService.updateAccess(accessNum.getId());
        jsonObject.put("result", result);
        return jsonObject;
    }
}
