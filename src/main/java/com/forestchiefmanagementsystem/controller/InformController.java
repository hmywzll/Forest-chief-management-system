package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forestchiefmanagementsystem.pojo.Inform;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.pojo.Woods;
import com.forestchiefmanagementsystem.pojo.dto.Dto;
import com.forestchiefmanagementsystem.service.InformService;
import com.forestchiefmanagementsystem.service.PersonalInformationService;
import com.forestchiefmanagementsystem.service.WoodsService;
import com.forestchiefmanagementsystem.util.DtoUtil;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.TableUI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/inform")
@ResponseBody
@Slf4j
public class InformController {
    @Autowired
    InformService informService;
    @Autowired
    WoodsService woodsService;
    @Autowired
    PersonalInformationService personalInformationService;


    @PostMapping
    public R<String> addInform(@RequestBody Inform inform){

        inform.setSendPersonId(ThreadUtil.getPIId());

        if (ThreadUtil.getPIPosition()!=0){
            PersonalInformation PI = personalInformationService.getById(ThreadUtil.getPIId());
            inform.setWoodsId(PI.getWoodsId());
        }

        boolean save = informService.save(inform);
        if (save){
            return R.success("发布通知成功");
        }else {
            return R.error("发布通知失败");
        }
    }

    /**
     * 获取我的通知
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/my_inform")
    public R<IPage<Dto>> getMyInform(Integer current, Integer size){
        IPage<Inform> informIPage=new Page<>(current,size);
        Woods byId = woodsService.getById(personalInformationService.getById(ThreadUtil.getPIId()).getWoodsId());

        IPage<Inform> page = informService.page(informIPage, new LambdaQueryWrapper<Inform>().eq(!ThreadUtil.getPIId().equals("1"),Inform::getWoodsId, byId.getId()).eq(!ThreadUtil.getPIId().equals("1"),Inform::getPosition, ThreadUtil.getPIPosition()));
        if (page.getRecords().size()==0)return R.success(new Page<Dto>(current,size));

        List<Inform> records = page.getRecords();

        List<String> pICollect = records.stream().map(Inform::getSendPersonId).collect(Collectors.toList());
        List<String> woodsCollect = records.stream().map(Inform::getWoodsId).collect(Collectors.toList());
        List<PersonalInformation> pIList = personalInformationService.list(new LambdaQueryWrapper<PersonalInformation>().in(PersonalInformation::getId, pICollect));
        List<Woods> woodsList = woodsService.list(new LambdaQueryWrapper<Woods>().in(Woods::getId,woodsCollect));

        ArrayList<Dto> dtoList = DtoUtil.getDtoList(records, Inform::getSendPersonId, pIList, PersonalInformation::getId);
        dtoList = DtoUtil.getDtoList(dtoList, Inform.class, Inform::getWoodsId, woodsList, Woods::getId);

        IPage<Dto> dtoIPage = new Page<>();
        BeanUtils.copyProperties(page,dtoIPage,"records");
        dtoIPage.setRecords(dtoList);

        return R.success(dtoIPage);
    }

    /**
     * 获取我发送的通知
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/my_send_inform")
    public R<IPage<Dto>> getMySendInform(Integer current, Integer size){
        IPage<Inform> informIPage=new Page<>(current,size);

        IPage<Inform> page = informService.page(informIPage,new LambdaQueryWrapper<Inform>().eq(Inform::getSendPersonId,ThreadUtil.getPIId()));
        if (page.getRecords().size()==0)return R.success(new Page<Dto>(current,size));

        List<Inform> records = page.getRecords();

        List<String> pICollect = records.stream().map(Inform::getSendPersonId).collect(Collectors.toList());
        List<String> woodsCollect = records.stream().map(Inform::getWoodsId).collect(Collectors.toList());
        List<PersonalInformation> pIList = personalInformationService.list(new LambdaQueryWrapper<PersonalInformation>().in(PersonalInformation::getId, pICollect));
        List<Woods> woodsList = woodsService.list(new LambdaQueryWrapper<Woods>().in(Woods::getId,woodsCollect));

        ArrayList<Dto> dtoList = DtoUtil.getDtoList(records, Inform::getSendPersonId, pIList, PersonalInformation::getId);
        dtoList = DtoUtil.getDtoList(dtoList, Inform.class, Inform::getWoodsId, woodsList, Woods::getId);

        IPage<Dto> dtoIPage = new Page<>();
        BeanUtils.copyProperties(page,dtoIPage,"records");
        dtoIPage.setRecords(dtoList);

        return R.success(dtoIPage);
    }

    /**
     * 删除通知
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete_inform(String id){
        boolean b = informService.removeById(id);
        if (b){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }
}
