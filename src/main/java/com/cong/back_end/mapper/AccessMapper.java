package com.cong.back_end.mapper;

import com.cong.back_end.pojo.AccessNum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-28 下午 09:34
 * @Project_name back_end
 */
@Mapper
@Repository
public interface AccessMapper {
    
    // 获得前天、昨天、今天的访问量
    List<AccessNum> getThreeAccess();
    
    // 更新今天的访问量
    int updateAccess(int id);
}