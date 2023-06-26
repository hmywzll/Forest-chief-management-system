package com.forestchiefmanagementsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.forestchiefmanagementsystem.pojo.Task;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hm
* @description 针对表【task(任务)】的数据库操作Service
* @createDate 2023-05-14 15:37:31
*/
public interface TaskService extends IService<Task> {

    public IPage<Task> getSendTask(Integer current,Integer size , Integer type);

    public IPage<Task> getReceptionPersonTask(Integer current, Integer size, Integer type);
}
