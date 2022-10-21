package com.cong.back_end.service;

import com.cong.back_end.mapper.UserMapper;
import com.cong.back_end.pojo.User;
import com.cong.back_end.util.AccountGeneration;
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
    
    @Override
    public String registerNewUser(User user) {
        // 首先进行邮箱唯一性验证
        User temp = userMapper.selectUserByEmail(user);
        if (temp != null) {
            return null;
        }
        // 插入新用户
        userMapper.insertNewUser(user);
        // 更新账号
        user.setAccount(AccountGeneration.getAccountById(user.getId()));
        userMapper.updateAccount(user);
        return user.getAccount();
    }
    
    @Override
    public boolean updatePwd(User user) {
        int num = userMapper.updatePwd(user);
        return num != 0;
    }
    
    @Override
    public boolean haveUser(User user) {
        User temp = userMapper.selectUserByEmail(user);
        if (temp != null) {
            return true;
        }
        return false;
    }
}
