package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.pojo.Token;
import com.cong.back_end.pojo.User;
import com.cong.back_end.service.UserService;
import com.cong.back_end.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    private Token token;
    
    @PostMapping("/login")
    public JSONObject login(User user) {
        System.out.println(user);
        jsonObject = new JSONObject();
        User resultUser = userService.selectUser(user);
        // 隐私保护
        if (resultUser == null) {
            jsonObject.put("result", false);
        } else {
            jsonObject.put("result", true);
            jsonObject.put("username", resultUser.getUsername());
            token = new Token();
            String access_token = TokenUtils.sign(resultUser);
            token.setToken(access_token);
            String refreshToken = TokenUtils.signForRefresh(resultUser);
            token.setRefreshToken(refreshToken);
            jsonObject.put("token", token);
        }
        return jsonObject;
    }
    
    @PostMapping("/register")
    public JSONObject register(User user, HttpSession httpSession) {
        // System.out.println(user);
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
    
    @PostMapping("/forgetPwdByEmail")
    public JSONObject forgetPwdByEmail(User user, HttpSession httpSession) {
        System.out.println("=====forgetPwdByEmail=====");
        System.out.println(user);
        //比对验证码
        jsonObject = new JSONObject();
        if (userService.haveUser(user)) {
            jsonObject.put("have", true);
            if (user.getCode().equals(httpSession.getAttribute("checkCode"))) {
                jsonObject.put("result", true);
                jsonObject.put("msg", "身份核验成功");
            } else {
                jsonObject.put("result", false);
                jsonObject.put("msg", "验证码不正确，请重新输入");
            }
        } else {
            jsonObject.put("have", false);
            jsonObject.put("msg", "未找到此用户");
        }

        return jsonObject;
    }
    
    @PostMapping("/forgetPwdByPassword")
    public JSONObject forgetPwdByPassword(User user) {
        System.out.println("=====forgetPwdByPassword=====");
        System.out.println(user);
        // 更改密码
        jsonObject = new JSONObject();
        boolean result = userService.updatePwd(user);
        jsonObject.put("result", result);
        if (result) {
            jsonObject.put("msg", "密码修改成功");
        } else {
            jsonObject.put("msg", "密码修改失败");
        }
        return jsonObject;
    }
}
