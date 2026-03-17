package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forestchiefmanagementsystem.pojo.*;
import com.forestchiefmanagementsystem.pojo.dto.Dto;
import com.forestchiefmanagementsystem.pojo.dto.PatrolPI;
import com.forestchiefmanagementsystem.pojo.dto.TaskPI;
import com.forestchiefmanagementsystem.service.*;
import com.forestchiefmanagementsystem.util.DtoUtil;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
@ResponseBody
@Slf4j
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    PatrolService patrolService;
    @Autowired
    PersonalInformationService personalInformationService;

    @Autowired
    LabelExampleService labelExampleService;
   /**
     * 添加task
     * @param task
     * @return
     */
    @PostMapping
    public R<String> addTask(@RequestBody Task task){

        //设置发送人id
        task.setSendPersonId(ThreadUtil.getPIId());

        //保存任务
        boolean save = taskService.save(task);

        //返回结果
        return save?R.success("任务添加成功"):R.error("任务添加失败");
    }

   /**
     * 获取我的任务，派遣任务，巡护任务信息
     * @param current
     * @param size
     * @param one
     * @param two
     * @return
     */
    @GetMapping
    public R<IPage<Dto>> getTask(Integer current,Integer size,Integer one,Integer two) throws InstantiationException, IllegalAccessException {
        //获取接收任务信息
        IPage<Task> receptionPersonTask = one==0?taskService.getReceptionPersonTask(current, size ,two-1):taskService.getSendTask(current, size ,two-1);
        //如果查询结果为空，返回空
        if (receptionPersonTask.getRecords().size()==0)return R.success(new Page<>());
        //获取任务列表
        List<Task> taskList = receptionPersonTask.getRecords();
        //获取发送和接收任务id
        List<String> collect1 = taskList.stream().map(one == 0 ?Task::getSendPersonId : Task::getReceptionPersonId).collect(Collectors.toList());
        List<String> collect2 = taskList.stream().map(Task::getLabelId).collect(Collectors.toList());

        //根据任务id查询个人信息
        List<PersonalInformation> pIList = personalInformationService.list(new LambdaQueryWrapper<PersonalInformation>().in(PersonalInformation::getId, collect1));

        //根据任务id查询标签信息
        List<LabelExample> labelExampleList=labelExampleService.list(new LambdaQueryWrapper<LabelExample>().in(LabelExample::getId,collect2));

        //根据任务信息，个人信息，标签信息，构建dto
        ArrayList<Dto> dtoList = DtoUtil.getDtoList(taskList, one == 0 ?Task::getSendPersonId:Task::getReceptionPersonId, pIList, PersonalInformation::getId);
        dtoList=DtoUtil.getDtoList(dtoList,Task.class,Task::getLabelId,labelExampleList,LabelExample::getId);

        //构建分页信息
        Page<Dto> dtoPage = new Page<>();
        BeanUtils.copyProperties(receptionPersonTask,dtoPage,"records");
        dtoPage.setRecords(dtoList);

        return R.success(dtoPage);
    }

   /**
     * 提交任务
     * @return
     */
    @PutMapping("/submit_task")
    public R<String> submitTask(@RequestBody Task t){
        //判断任务类型
        if (t.getTaskType()==1){
            //获取标签信息
            LabelExample one = labelExampleService.getOne(new LambdaQueryWrapper<LabelExample>().eq(LabelExample::getId, t.getLabelId()));
            //判断标签状态
            if (one.getExampleStatus()==0)R.error("事件未完成");
        }

        //创建任务
        Task task = new Task();
        task.setId(t.getId());
        task.setTaskStatus(1);
        task.setTaskReport(t.getTaskReport());
        task.setTaskImgs(t.getTaskImgs());
        task.setTaskCompletion(new Date());

        //更新任务
        boolean b = taskService.updateById(task);
        if (b){
            return R.success("任务提交成功");
        }else {
            return R.error("任务提交失败");
        }
    }
}
