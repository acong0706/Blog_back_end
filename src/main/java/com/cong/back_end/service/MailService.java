package com.cong.back_end.service;

/**
 * @Author Cong
 * @Create_time 2022-10-10 下午 02:25
 * @Project_name back_end
 */
public interface MailService {
    
    void sendSimpleMail(String to, String title, String content);
}
