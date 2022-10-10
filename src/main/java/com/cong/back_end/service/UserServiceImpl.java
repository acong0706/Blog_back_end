package com.cong.back_end.service;

import com.cong.back_end.mapper.UserMapper;
import com.cong.back_end.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Cong
 * @Create_time 2022-10-09 下午 03:36
 * @Project_name back_end
 */
 
@Service
public class UserServiceImpl implements UserService {
    
    private UserMapper userMapper;
    
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public User selectUser(User user) {
        return userMapper.selectUser(user);
    }
}
