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

        //设置labelPersonalId
        labelExample.setLabelPersonalId(ThreadUtil.getPIId());

        //保存label example
        boolean save = labelExampleService.save(labelExample);

        return save?R.success("添加成功"):R.error("添加失败");
    }

   /**
     * 获取所有事件example
     * @return
     */
    @GetMapping("/event")
    public R<List<LabelExample>> getEventExample(){
        //创建LambdaQueryWrapper对象，用于查询Label表中labelType为1的事件
        LambdaQueryWrapper<Label> labelLambdaQueryWrapper=new LambdaQueryWrapper<>();
        labelLambdaQueryWrapper.eq(Label::getLabelType,1);
        //根据LambdaQueryWrapper查询Label表中的数据
        List<Label> list = labelService.list(labelLambdaQueryWrapper);
        //将查询到的Label表中的id存入list中
        List<String> collect = list.stream().map(Label::getId).collect(Collectors.toList());

        //创建LambdaQueryWrapper对象，用于查询LabelExample表中labelId在collect中的数据
        LambdaQueryWrapper<LabelExample> lqw = new LambdaQueryWrapper<>();
        lqw.in(LabelExample::getLabelId,collect);
        //根据LambdaQueryWrapper查询LabelExample表中的数据
        List<LabelExample> list1 = labelExampleService.list(lqw);
        //返回查询到的数据
        return R.success(list1);
    }

   /**
     * 获取该实例的所有相关信息
     * @param labelE
     * @return
     */
    @GetMapping("/all_correlation")
    public R<Dto> getAllCorrelation(LabelExample labelE){

        //根据labelE获取LabelExample实例
        LabelExample labelExample = labelExampleService.getById(labelE);
        //根据labelExample.getLabelId()获取Label实例
        Label label = labelService.getById(labelExample.getLabelId());
        //根据labelExample.getLabelPersonalId()获取PersonalInformation实例
        PersonalInformation pi = personalInformationService.getById(labelExample.getLabelPersonalId());

        Dto dto = new Dto();
        HashMap<String, Object> dtoMap = dto.getDtoMap();

        //如果labelExample.getExampleStatus()等于1
        if (labelExample.getExampleStatus()==1){
            //根据labelExample.getDealWithPersonId()获取PersonalInformation实例
            PersonalInformation pi1 = personalInformationService.getById(labelExample.getDealWithPersonId());
            //将PersonalInformation实例放入dtoMap中
            dtoMap.put("DealWith"+pi1.getClass().getSimpleName(),pi1);
        }
        //将LabelExample实例放入dtoMap中
        dtoMap.put(labelExample.getClass().getSimpleName(),labelExample);
        //将Label实例放入dtoMap中
        dtoMap.put(label.getClass().getSimpleName(),label);
        //将PersonalInformation实例放入dtoMap中
        dtoMap.put(pi.getClass().getSimpleName(),pi);

        //返回dto
        return R.success(dto);
    }

   /**
     * 提交事件
     * @param labelExample
     * @return
     */
    @PutMapping("/submit")
    public R<String> submitLabel(@RequestBody LabelExample labelExample){

        //设置处理人ID
        labelExample.setDealWithPersonId(ThreadUtil.getPIId());
        //设置处理时间
        labelExample.setDealWithTime(new Date());
        //设置处理状态
        labelExample.setExampleStatus(1);
        //更新处理表
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

        //根据id获取label_example
        LabelExample labelExample = labelExampleService.getById(id);
        //根据label_example的labelId获取label
        Label label = labelService.getById(labelExample.getLabelId());

        //如果label的类型为1，则检查是否有任务关联
        if (label.getLabelType()==1){
            List<Task> list = taskService.list(new LambdaQueryWrapper<Task>().eq(Task::getSendPersonId, id));
            if (list.size()!=0)return R.error("有关联任务无法删除");
        }

        //根据id删除label_example
        boolean b = labelExampleService.removeById(id);
        if (b){
            return R.success("删除标注成功");
        }else {
            return R.success("删除标注失败");
        }
    }
}
