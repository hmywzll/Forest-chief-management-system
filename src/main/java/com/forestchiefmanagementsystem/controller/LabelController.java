package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forestchiefmanagementsystem.pojo.Label;
import com.forestchiefmanagementsystem.pojo.LabelExample;
import com.forestchiefmanagementsystem.service.LabelExampleService;
import com.forestchiefmanagementsystem.service.LabelService;
import com.forestchiefmanagementsystem.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/label")
@ResponseBody
@Slf4j
public class LabelController {

    @Autowired
    LabelService labelService;
    @Autowired
    LabelExampleService labelExampleService;

    /**
     * 添加label
     */
    List<Integer> eventType= new ArrayList<>(Arrays.asList(0, 1));
    @PostMapping
    public R<String> addLabel(@RequestBody Label label){
        if (!eventType.contains(label.getLabelType())){
            return R.error("非法事件类型");
        }
        Label one = labelService.getOne(new LambdaQueryWrapper<Label>().eq(Label::getLabelName, label.getLabelName()));
        if (one!=null){
            return R.error(label.getLabelName()+"标注已存在");
        }
        boolean save = labelService.save(label);
        return R.success("标注添加成功");
    }

    /**
     * 获取所有label
     * @return
     */
    @GetMapping
    public R<List<Label>> getLabel(){

        List<Label> list = labelService.list();

        return R.success(list);
    }

    /**
     * 分页获取label
     * @param size
     * @param current
     * @return
     */
    @GetMapping("/page")
    public R<IPage<Label>> getLabelPage(Integer size,Integer current){

        IPage<Label> page = new Page<>(current,size);

        labelService.page(page);

        return R.success(page);
    }

    /**
     * 根据id删除label
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteLabel(String id){

//        管理员可删除
        Label label = labelService.getById(id);

        List<LabelExample> list = labelExampleService.list(new LambdaQueryWrapper<LabelExample>().eq(LabelExample::getLabelId, label.getId()));
        if (list.size()!=0){
            return R.error("地图上该标注不为空，无法删除");
        }

        boolean b = labelService.removeById(id);
        if (b){
            return R.success("删除标注成功成功");
        }else {
            return R.error("删除标注失败");
        }

    }
}
