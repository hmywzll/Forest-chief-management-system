package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.forestchiefmanagementsystem.pojo.Label;
import com.forestchiefmanagementsystem.pojo.LabelExample;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.pojo.Task;
import com.forestchiefmanagementsystem.pojo.dto.Dto;
import com.forestchiefmanagementsystem.service.LabelExampleService;
import com.forestchiefmanagementsystem.service.LabelService;
import com.forestchiefmanagementsystem.service.PersonalInformationService;
import com.forestchiefmanagementsystem.service.TaskService;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/label_example")
@ResponseBody
@Slf4j
public class LabelExampleController {
    @Autowired
    LabelExampleService labelExampleService;
    @Autowired
    LabelService labelService;

    @Autowired
    TaskService taskService;

    @Autowired
    PersonalInformationService personalInformationService;

    /**
     * 获取所有label example
     * @return
     */
    @GetMapping
    public R<List<LabelExample>> getLabelExample(){

        List<LabelExample> list = labelExampleService.list();

        return R.success(list);
    }

    /**
     * 添加label example
     * @param labelExample
     * @return
     */
    @PostMapping
    public R<String> addLabelExample(@RequestBody LabelExample labelExample){

        labelExample.setLabelPersonalId(ThreadUtil.getPIId());

        boolean save = labelExampleService.save(labelExample);

        return save?R.success("添加成功"):R.error("添加失败");
    }

    /**
     * 获取所有事件example
     * @return
     */
    @GetMapping("/event")
    public R<List<LabelExample>> getEventExample(){
        LambdaQueryWrapper<Label> labelLambdaQueryWrapper=new LambdaQueryWrapper<>();
        labelLambdaQueryWrapper.eq(Label::getLabelType,1);
        List<Label> list = labelService.list(labelLambdaQueryWrapper);
        List<String> collect = list.stream().map(Label::getId).collect(Collectors.toList());

        LambdaQueryWrapper<LabelExample> lqw = new LambdaQueryWrapper<>();
        lqw.in(LabelExample::getLabelId,collect);
        List<LabelExample> list1 = labelExampleService.list(lqw);
        return R.success(list1);
    }

    /**
     * 获取该实例的所有相关信息
     * @param labelE
     * @return
     */
    @GetMapping("/all_correlation")
    public R<Dto> getAllCorrelation(LabelExample labelE){

        LabelExample labelExample = labelExampleService.getById(labelE);
        Label label = labelService.getById(labelExample.getLabelId());
        PersonalInformation pi = personalInformationService.getById(labelExample.getLabelPersonalId());

        Dto dto = new Dto();
        HashMap<String, Object> dtoMap = dto.getDtoMap();

        if (labelExample.getExampleStatus()==1){
            PersonalInformation pi1 = personalInformationService.getById(labelExample.getDealWithPersonId());
            dtoMap.put("DealWith"+pi1.getClass().getSimpleName(),pi1);
        }
        dtoMap.put(labelExample.getClass().getSimpleName(),labelExample);
        dtoMap.put(label.getClass().getSimpleName(),label);
        dtoMap.put(pi.getClass().getSimpleName(),pi);

        return R.success(dto);
    }

    /**
     * 提交事件
     * @param labelExample
     * @return
     */
    @PutMapping("/submit")
    public R<String> submitLabel(@RequestBody LabelExample labelExample){

        labelExample.setDealWithPersonId(ThreadUtil.getPIId());
        labelExample.setDealWithTime(new Date());
        labelExample.setExampleStatus(1);
        boolean b = labelExampleService.updateById(labelExample);

        if (b){
            return R.success("事件提交处理成功");
        }else {
            return R.error("事件提交处理失败");
        }
    }

    /**
     * 删除label_example
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteLabelExample(String id){

        LabelExample labelExample = labelExampleService.getById(id);
        Label label = labelService.getById(labelExample.getLabelId());

        if (label.getLabelType()==1){
            List<Task> list = taskService.list(new LambdaQueryWrapper<Task>().eq(Task::getSendPersonId, id));
            if (list.size()!=0)return R.error("有关联任务无法删除");
        }

        boolean b = labelExampleService.removeById(id);
        if (b){
            return R.success("删除标注成功");
        }else {
            return R.success("删除标注失败");
        }
    }
}
