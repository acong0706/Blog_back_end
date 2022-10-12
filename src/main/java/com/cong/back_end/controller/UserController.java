package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.pojo.User;
import com.cong.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
        jsonObject = new JSONObject();
        User result = userService.selectUser(user);
        if (result == null) {
            jsonObject.put("result", false);
        } else {
            jsonObject.put("result", true);
        }
        return jsonObject;
    }
    
    @PostMapping("/register")
    public JSONObject register(User user, HttpSession httpSession) {
        jsonObject = new JSONObject();
        if (user.getCode().equals(httpSession.getAttribute("checkCode"))) {
            String account = userService.registerNewUser(user);
            if (account == null) {
                jsonObject.put("result", false);
                jsonObject.put("msg", "邮箱已使用，请更换其他邮箱");
            } else {
                jsonObject.put("result", true);
                jsonObject.put("msg", "注册成功，您的账号为 " + account);
            }
        } else {
            jsonObject.put("result", false);
            jsonObject.put("msg", "验证码不正确，请重新输入");
        }
        return jsonObject;
    }
}
