package com.forestchiefmanagementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forestchiefmanagementsystem.pojo.Task;
import com.forestchiefmanagementsystem.service.TaskService;
import com.forestchiefmanagementsystem.mapper.TaskMapper;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import org.springframework.stereotype.Service;

/**
* @author hm
* @description 针对表【task(任务)】的数据库操作Service实现
* @createDate 2023-05-14 15:37:31
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{
    @Override
    public IPage<Task> getSendTask(Integer current, Integer size ,Integer type) {

        IPage<Task> page=new Page<>(current,size);
        LambdaQueryWrapper<Task> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Task::getSendPersonId,ThreadUtil.getPIId());
        lambdaQueryWrapper.eq(type>=0,Task::getTaskStatus,type);
        return page(page,lambdaQueryWrapper);
    }

    @Override
    public IPage<Task> getReceptionPersonTask(Integer current, Integer size, Integer type) {
        IPage<Task> page=new Page<>(current,size);
        LambdaQueryWrapper<Task> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Task::getReceptionPersonId,ThreadUtil.getPIId());
        lambdaQueryWrapper.eq(type>=0,Task::getTaskStatus,type);
        return page(page,lambdaQueryWrapper);
    }
}




