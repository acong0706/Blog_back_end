package com.cong.back_end.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author Cong
 * @Create_time 2022-10-10 下午 02:26
 * @Project_name back_end
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService{
    
    @Value("${spring.mail.username}")
    private String from;
    
    private JavaMailSender javaMailSender;
    
    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    @Override
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        // System.out.println(mailMessage);
        javaMailSender.send(mailMessage);
        log.info("邮件发送成功");
    }
}
