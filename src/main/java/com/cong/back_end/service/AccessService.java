package com.cong.back_end.service;

import com.cong.back_end.pojo.AccessNum;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-28 下午 09:37
 * @Project_name back_end
 */
public interface AccessService {
    
    // 获得前天、昨天、今天的访问量
    List<AccessNum> getThreeAccess();
    
    // 更新今天的访问量
    int updateAccess(int id);
}
