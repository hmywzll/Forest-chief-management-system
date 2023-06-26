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

        task.setSendPersonId(ThreadUtil.getPIId());

        boolean save = taskService.save(task);

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
        IPage<Task> receptionPersonTask = one==0?taskService.getReceptionPersonTask(current, size ,two-1):taskService.getSendTask(current, size ,two-1);
        if (receptionPersonTask.getRecords().size()==0)return R.success(new Page<>());
        List<Task> taskList = receptionPersonTask.getRecords();
        List<String> collect1 = taskList.stream().map(one == 0 ?Task::getSendPersonId : Task::getReceptionPersonId).collect(Collectors.toList());
        List<String> collect2 = taskList.stream().map(Task::getLabelId).collect(Collectors.toList());

        List<PersonalInformation> pIList = personalInformationService.list(new LambdaQueryWrapper<PersonalInformation>().in(PersonalInformation::getId, collect1));

        List<LabelExample> labelExampleList=labelExampleService.list(new LambdaQueryWrapper<LabelExample>().in(LabelExample::getId,collect2));

        ArrayList<Dto> dtoList = DtoUtil.getDtoList(taskList, one == 0 ?Task::getSendPersonId:Task::getReceptionPersonId, pIList, PersonalInformation::getId);
        dtoList=DtoUtil.getDtoList(dtoList,Task.class,Task::getLabelId,labelExampleList,LabelExample::getId);

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
        if (t.getTaskType()==1){
            LabelExample one = labelExampleService.getOne(new LambdaQueryWrapper<LabelExample>().eq(LabelExample::getId, t.getLabelId()));
            if (one.getExampleStatus()==0)R.error("事件未完成");
        }

        Task task = new Task();
        task.setId(t.getId());
        task.setTaskStatus(1);
        task.setTaskReport(t.getTaskReport());
        task.setTaskImgs(t.getTaskImgs());
        task.setTaskCompletion(new Date());

        boolean b = taskService.updateById(task);
        if (b){
            return R.success("任务提交成功");
        }else {
            return R.error("任务提交失败");
        }
    }
}
