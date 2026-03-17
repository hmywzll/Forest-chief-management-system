package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.pojo.Woods;
import com.forestchiefmanagementsystem.service.PersonalInformationService;
import com.forestchiefmanagementsystem.service.WoodsService;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@Controller
@RequestMapping("/woods")
@Slf4j
@ResponseBody
public class WoodsController {

    @Autowired
    WoodsService woodsService;
    @Autowired
    PersonalInformationService personalInformationService;
    /**
     * 返回林地数据
     * @return
     */
    @GetMapping
    public R<List<Woods>> getWoodsData(){
        List<Woods> list = woodsService.list();
        return R.success(list);
    }

   /**
     * 通过PI id返回林地数据
     * @return
     */
    @GetMapping("/by_pi_id")
    public R<List<Woods>> getWoodsDataByPi(){
        //获取PI id
        String piId = ThreadUtil.getPIId();
        //根据PI id获取PersonalInformation对象
        PersonalInformation pi = personalInformationService.getById(piId);
        //根据PI id和林地id获取林地数据
        List<Woods> list = woodsService.list(new LambdaQueryWrapper<Woods>().eq(!piId.equals("1"),Woods::getId,pi.getWoodsId()));
        return R.success(list);
    }

    /**
     * 添加林地
     * @param woods
     * @return
     */
    @PostMapping
    public R<String> saveWood(@RequestBody Woods woods){
        //检查林地名称是否重复
        Woods one = woodsService.getOne(new LambdaQueryWrapper<Woods>().eq(Woods::getWoodsName, woods.getWoodsName()));
        if (one!=null){
            return R.error("林地名称重复");
        }
        //检查林地编号是否重复
        one = woodsService.getOne(new LambdaQueryWrapper<Woods>().eq(Woods::getWoodsNumber, woods.getWoodsNumber()));
        if (one!=null){
            return R.error("林地编号重复");
        }

        try {
            //保存林地
            boolean save = woodsService.save(woods);
            return save?R.success("添加林地成功"):R.error("添加林地失败");
        }catch (Exception e){
            return R.error("添加林地失败");
        }
    }

   /**
     * 根据id获取林地
     * @param id
     * @return
     */
    @GetMapping("/by_id")
    public R<Woods> getById(String id){
        Woods woods = woodsService.getById(id);
        return R.success(woods);
    }

//    @RequestMapping(method = RequestMethod.DELETE)
//    public R<String> delete(){
//        woodsService.remove(new LambdaQueryWrapper<Woods>().gt(Woods::getId,1));
//        return R.success("ok");
//    }

//    @RequestMapping(method = RequestMethod.PUT)
//    public R<String> update(){
//        woodsService.update(new Woods(),new LambdaQueryWrapper<Woods>().gt(Woods::getId,1));
//        return R.success("ok");
//    }
}
