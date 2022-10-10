package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.pojo.User;
import com.cong.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @Author Cong
 * @Create_time 2022-10-08 下午 02:42
 * @Project_name back_end
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    private JSONObject jsonObject;
    
    @PostMapping("/login")
    public JSONObject login(User user) {
        // System.out.println("输入：" + user);
        User result = userService.selectUser(user);
        jsonObject = new JSONObject();
        // System.out.println("结果：" + result);
        if (result == null) {
            jsonObject.put("result", false);
        } else {
            jsonObject.put("result", true);
        }
        return jsonObject;
    }
}
