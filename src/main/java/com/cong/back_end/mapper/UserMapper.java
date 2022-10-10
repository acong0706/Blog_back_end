package com.cong.back_end.mapper;

import com.cong.back_end.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Cong
 * @Create_time 2022-10-09 下午 03:35
 * @Project_name back_end
 */
@Mapper
@Repository
public interface UserMapper {
    
    // 登录验证
    User selectUser(User user);
}
