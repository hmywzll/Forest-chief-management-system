package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forestchiefmanagementsystem.pojo.Inform;
import com.forestchiefmanagementsystem.pojo.Patrol;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.pojo.dto.Dto;
import com.forestchiefmanagementsystem.pojo.dto.PatrolDto;
import com.forestchiefmanagementsystem.service.PatrolService;
import com.forestchiefmanagementsystem.service.PersonalInformationService;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.DTD;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/patrol")
@ResponseBody
@Slf4j
public class PatrolController {

    @Autowired
    PatrolService patrolService;
    @Autowired
    PersonalInformationService personalInformationService;

    /**
     * 获取自己的巡护巡护数据
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/my_patrol")
    public R<IPage<Patrol>> getMyPatrolPage(Integer current,Integer size){

        IPage<Patrol> page=new Page<>(current,size);
        patrolService.page(page,new LambdaQueryWrapper<Patrol>().eq(Patrol::getPatrolPersonId, ThreadUtil.getPIId()).eq(Patrol::getPatrolStatus,1));

        return R.success(page);
    }

    /**
     * 开始巡护
     * @param patrol
     * @return
     */
    @PostMapping
    public R<Patrol> startPatrol(@RequestBody Patrol patrol){
        patrol.setPatrolPersonId(ThreadUtil.getPIId());
        patrol.setStartTime(new Date());
        boolean save = patrolService.save(patrol);

        Patrol one = patrolService.getOne(new LambdaQueryWrapper<Patrol>().eq(Patrol::getPatrolStatus, 0));

        if (save){
            return R.success(one);
        }else {
            return R.error("开始巡护失败");
        }
    }

    /**
     * 结束巡护
     * @param patrol
     * @return
     */
    @PutMapping
    public R<String> endPatrol(@RequestBody Patrol patrol){
        Patrol pat = new Patrol();
        pat.setId(patrol.getId());
        pat.setPatrolMileage(patrol.getPatrolMileage());
        pat.setPatrolContent(patrol.getPatrolContent());
        pat.setPatrolImgs(patrol.getPatrolImgs());
        pat.setEndTime(new Date());
        pat.setPatrolStatus(1);
        Double d= (double) (pat.getEndTime().getTime()-patrol.getStartTime().getTime());
        pat.setPatrolDuration(d);

        boolean b = patrolService.updateById(pat);
        if (b){
            return R.success("结束巡护成功");
        }else {
            return R.error("结束巡护失败");
        }
    }

    /**
     * 获取正在巡护中的数据
     * @return
     */
    @GetMapping("/run")
    public R<Patrol> getRunPatrol(){
        Patrol one = patrolService.getOne(new LambdaQueryWrapper<Patrol>().eq(Patrol::getPatrolStatus, 0));
        return R.success(one);
    }


    /**
     * 获取PatrolPage
     * @param current
     * @param size
     * @return
     */
    @GetMapping
    public R<IPage<PatrolDto>> getPatrolPage(Integer current,Integer size){

        PersonalInformation myPi = personalInformationService.getById(ThreadUtil.getPIId());
        IPage<PersonalInformation> page=new Page<>(current,size);


        if (myPi.getId().equals("1")){
            personalInformationService.page(page);
        }else if (!myPi.getPosition().equals(3)){
            LambdaQueryWrapper<PersonalInformation> personalInformationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            personalInformationLambdaQueryWrapper.gt(PersonalInformation::getPosition,myPi.getPosition()).eq(PersonalInformation::getWoodsId,myPi.getWoodsId());
            personalInformationService.page(page,personalInformationLambdaQueryWrapper);
        }
        List<PersonalInformation> records = page.getRecords();
        List<String> pIIds = records.stream().map(PersonalInformation::getId).collect(Collectors.toList());
        List<PatrolDto> patrolPage =pIIds.size()!=0?patrolService.getPatrolPage(pIIds):new ArrayList<PatrolDto>();


        IPage<PatrolDto> patrolDtoPage = new Page<>(current,size);

        BeanUtils.copyProperties(page,patrolDtoPage,"records");

        patrolDtoPage.setRecords(patrolPage);

        return R.success(patrolDtoPage);
    }

    /**
     * 根据id获取巡护数据
     * @param id
     * @return
     */
    @GetMapping("/id_patrol")
    public R<List<Patrol>> getMyPatrolPage(String id){


        List<Patrol> list = patrolService.list(new LambdaQueryWrapper<Patrol>().eq(Patrol::getPatrolPersonId, id).eq(Patrol::getPatrolStatus, 1));

        return R.success(list);
    }

}