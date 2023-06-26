package com.forestchiefmanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forestchiefmanagementsystem.pojo.Inform;
import com.forestchiefmanagementsystem.pojo.Patrol;
import com.forestchiefmanagementsystem.pojo.dto.PatrolDto;
import com.forestchiefmanagementsystem.service.PatrolService;
import com.forestchiefmanagementsystem.mapper.PatrolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hm
* @description 针对表【patrol(巡护)】的数据库操作Service实现
* @createDate 2023-05-14 15:37:31
*/
@Service
public class PatrolServiceImpl extends ServiceImpl<PatrolMapper, Patrol> implements PatrolService{
    @Autowired
    PatrolMapper patrolMapper;
    @Override
    public List<PatrolDto> getPatrolPage(List idList) {
        return patrolMapper.getPatrolPage(idList);
    }
}




