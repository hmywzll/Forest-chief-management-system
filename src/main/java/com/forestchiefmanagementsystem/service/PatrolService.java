package com.forestchiefmanagementsystem.service;

import com.forestchiefmanagementsystem.pojo.Patrol;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forestchiefmanagementsystem.pojo.dto.PatrolDto;

import java.util.List;

/**
* @author hm
* @description 针对表【patrol(巡护)】的数据库操作Service
* @createDate 2023-05-14 15:37:31
*/
public interface PatrolService extends IService<Patrol> {
    public List<PatrolDto> getPatrolPage(List idList);
}
