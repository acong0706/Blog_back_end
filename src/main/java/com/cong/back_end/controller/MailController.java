package com.cong.back_end.controller;

import com.cong.back_end.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    
    // @PostMapping("/getCheckCode")
    // public RestResponse<String> getCheckCode() {
    //
    // }
}
