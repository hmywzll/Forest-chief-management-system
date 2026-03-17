package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forestchiefmanagementsystem.pojo.Account;
import com.forestchiefmanagementsystem.pojo.Inform;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.service.AccountService;
import com.forestchiefmanagementsystem.service.PersonalInformationService;
import com.forestchiefmanagementsystem.service.WoodsService;
import com.forestchiefmanagementsystem.util.ColourTest;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hm
 */
@Controller
@RequestMapping("/personal_information")
@ResponseBody
@Slf4j
public class PersonalInformationController{
    @Autowired
    PersonalInformationService personalInformationService;
    @Autowired
    WoodsService woodsService;
    @Autowired
    AccountService accountService;

    /**
     * 获取可能的上级列表
     * @return
     */
//     @GetMapping("/get_superiors")
//     //获取指定职位和部门下的上级
// public R<List<PersonalInformation>> getSuperiors(Integer position,String woodsId){
//
//         LambdaQueryWrapper<PersonalInformation> pILambdaQueryWrapper = new LambdaQueryWrapper<>();
// //        判断是否为管理员，管理员上级固定为id为1的管理员
//         if (position>0){
//             pILambdaQueryWrapper
//                     .eq(PersonalInformation::getPosition, position-1)
//                     .eq(PersonalInformation::getWoodsId,woodsId);
//             List<PersonalInformation> list = personalInformationService.list(pILambdaQueryWrapper);
//             System.out.println(position-1);
// //            System.out.println(one.getSuperiorId());
//             System.out.println(list);
//         }else if (position==0){
//             pILambdaQueryWrapper.eq(PersonalInformation::getId,1);
//         }
//
//         List<PersonalInformation> list = personalInformationService.list(pILambdaQueryWrapper);
//         return R.success(list);
//     }
    @GetMapping("/get_superiors")
    public R<List<PersonalInformation>> getSuperiors(Integer position, String woodsId) {
        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<PersonalInformation> pILambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 根据position值判断是否为管理员,id为1
        if (position > 0) {
            // 如果是管理员，则将position值减1，并将woodsId作为查询条件
            pILambdaQueryWrapper
                    .eq(PersonalInformation::getPosition, position - 1)
                    .eq(PersonalInformation::getWoodsId, woodsId);
        } else if (position == 0) {
            // 如果position值为0，则表示查询管理员
            pILambdaQueryWrapper.eq(PersonalInformation::getId, 1);
        }

        // 使用personalInformationService.list()方法查询满足条件的PersonalInformation对象列表
        List<PersonalInformation> list = personalInformationService.list(pILambdaQueryWrapper);

        // 打印查询条件对应的position值
        System.out.println(position - 1);

        // 返回一个包含查询结果的R<List<PersonalInformation>>对象
        return R.success(list);
    }

    /**
     * 添加个人信息和创建账户 账号名和密码默认电话号码
     * @param pI
     * @return
     */
    @PostMapping("/add_pi")
    @Transactional
    public R<String> addPI(@RequestBody PersonalInformation pI){

        //判断手机号是否重复
        PersonalInformation one = personalInformationService.getOne(new LambdaQueryWrapper<PersonalInformation>().eq(PersonalInformation::getUsername, pI.getUsername()));
        if (one!=null){
            return R.error("名称重复");
        }
        one = personalInformationService.getOne(new LambdaQueryWrapper<PersonalInformation>().eq(PersonalInformation::getPhoneNumber, pI.getPhoneNumber()));
        if (one!=null){
            return R.error("电话号码重复");
        }
        //保存个人信息
        boolean save = personalInformationService.save(pI);
        //添加账号
        Account account = new Account();
        account.setUser(pI.getPhoneNumber());
        account.setPassword(pI.getPhoneNumber());
        account.setPersonalInformationId(pI.getId());
        boolean accountSave=accountService.save(account);
        return save&&accountSave?R.success("添加成功"):R.error("添加失败");
    }

   /**
     * 查询个人信息表
     * @param size
     * @param current
     * @return
     */
    @GetMapping
    public R<IPage> getPage(Integer size,Integer current){
        PersonalInformation pi = personalInformationService.getById(ThreadUtil.getPIId());

        // 创建LambdaQueryWrapper对象，用于查询
        LambdaQueryWrapper<PersonalInformation> lqw = new LambdaQueryWrapper<>();
        // 查询职位大于等于当前职位
        lqw.ge(PersonalInformation::getPosition,ThreadUtil.getPIPosition());
        // 查询当前用户id和woodsId
        lqw.eq(!pi.getId().equals("1"),PersonalInformation::getWoodsId,pi.getWoodsId());
        // 查询id不等于当前用户id
        lqw.ne(PersonalInformation::getId,pi.getId());
        // 查询当前用户id和父级id
        lqw.or(!pi.getId().equals("1")).eq(!pi.getId().equals("1"),PersonalInformation::getId,pi.getSuperiorId());
        // 创建分页对象
        Page<PersonalInformation> page = new Page<>(current,size);
        // 查询
        personalInformationService.page(page, lqw);

        return R.success(page);
    }

   /**
     * 根据id获取一条数据
     * @param id
     * @return
     */
    @GetMapping("/one")
    public R<PersonalInformation> getOne(String id){

        //根据id查询一条数据
        PersonalInformation one = personalInformationService.getOne(new LambdaQueryWrapper<PersonalInformation>().eq(PersonalInformation::getId, id));
        //返回查询结果
        return R.success(one);
    }

    /**
     * 删除用户
     * 删除对应账户
     * @param id
     * @return
     */
    @DeleteMapping
    @Transactional
    public R<String> deletePI(String id){
        PersonalInformation pI = personalInformationService.getOne(new LambdaQueryWrapper<PersonalInformation>().eq(PersonalInformation::getId, id));
        boolean pIB = personalInformationService.removeById(id);
        boolean accountB = accountService.remove(new LambdaQueryWrapper<Account>().eq(Account::getPersonalInformationId, pI.getId()));
        if (pIB&&accountB){
            return R.success("删除用户成功");
        }else {
            return R.error("删除用户失败");
        }
    }


}
