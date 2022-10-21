package com.cong.back_end.controller;

import com.alibaba.fastjson.JSONObject;
import com.cong.back_end.pojo.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cong.back_end.util.TokenUtils.getNewTokenByRefreshToken;

/**
 * @Author Cong
 * @Create_time 2022-10-16 下午 10:26
 * @Project_name back_end
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    
    private JSONObject jsonObject;
    
    @PostMapping("/getNewToken")
    public JSONObject getNewToken(Token token) {
        // System.out.println(refreshToken);
        System.out.println(token);
        jsonObject = new JSONObject();
        String access_token = getNewTokenByRefreshToken(token.getRefreshToken());
        token.setToken(access_token);
        jsonObject.put("token", token);
        return jsonObject;
    }
}
