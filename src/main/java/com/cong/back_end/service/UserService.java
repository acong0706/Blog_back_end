package com.cong.back_end.service;

import com.cong.back_end.pojo.User;

/**
 * @Author Cong
 * @Create_time 2022-10-09 下午 03:36
 * @Project_name back_end
 */
public interface UserService {
    
    // 登录验证
    User selectUser(User user);
    
    // 注册
    String registerNewUser(User user);
}
