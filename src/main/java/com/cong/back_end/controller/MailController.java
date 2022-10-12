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
        // System.out.println(usersBo);
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "哈喽，亲爱的新用户哟！您的注册验证码为：" + checkCode;
        // System.out.println(message);
        try {
            // 邮箱发送功能
            mailService.sendSimpleMail(usersBo.getEmail(), "Cong的博客-注册验证码", message);
            httpSession.setAttribute("checkCode", checkCode);
        } catch (Exception e) {
            restResponse = RestResponse.buildFailureResp(e);
            return restResponse;
        }
        restResponse = RestResponse.buildSuccessResp();
        return restResponse;
    }
}
