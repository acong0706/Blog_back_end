package com.cong.back_end.controller;

import com.cong.back_end.pojo.UsersBo;
import com.cong.back_end.service.MailService;
import com.cong.back_end.util.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @Author Cong
 * @Create_time 2022-10-10 下午 02:43
 * @Project_name back_end
 */
@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    
    private MailService mailService;
    private RestResponse restResponse;
    
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    
    @PostMapping("/getCheckCode")
    public RestResponse getCheckCode(UsersBo usersBo, HttpSession httpSession) {
        String msg1 = "哈喽，亲爱的新用户哟！您的注册验证码为：";
        String msg2 = "Cong的博客-注册验证码";
        return checkCode(usersBo, httpSession, msg1, msg2);
    }
    
    @PostMapping("/getCheckCode2")
    public RestResponse getCheckCode2(UsersBo usersBo, HttpSession httpSession) {
        String msg1 = "哈喽，可爱的用户哟！您的密码重置验证码为：";
        String msg2 = "Cong的博客-密码重置验证码";
        return checkCode(usersBo, httpSession, msg1, msg2);
    }
    
    private RestResponse checkCode(UsersBo usersBo, HttpSession httpSession, String msg1, String msg2) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = msg1 + checkCode;
        try {
            // 邮箱发送功能
            mailService.sendSimpleMail(usersBo.getEmail(), msg2, message);
            httpSession.setAttribute("checkCode", checkCode);
        } catch (Exception e) {
            restResponse = RestResponse.buildFailureResp(e);
            return restResponse;
        }
        restResponse = RestResponse.buildSuccessResp();
        return restResponse;
    }
}
