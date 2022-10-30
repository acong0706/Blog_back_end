package com.cong.back_end.service;

import com.cong.back_end.mapper.AccessMapper;
import com.cong.back_end.mapper.ArticleMapper;
import com.cong.back_end.pojo.AccessNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Cong
 * @Create_time 2022-10-28 下午 09:38
 * @Project_name back_end
 */
@Service
public class AccessServiceImpl implements AccessService {
    
    private AccessMapper accessMapper;
    
    @Autowired
    public void setAccessMapper(AccessMapper accessMapper) {
        this.accessMapper = accessMapper;
    }
    
    @Override
    public List<AccessNum> getThreeAccess() {
        return accessMapper.getThreeAccess();
    }
    
    @Override
    public int updateAccess(int id) {
        return accessMapper.updateAccess(id);
    }
}
