package com.forestchiefmanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.forestchiefmanagementsystem.pojo.Account;
import com.forestchiefmanagementsystem.pojo.PersonalInformation;
import com.forestchiefmanagementsystem.service.AccountService;
import com.forestchiefmanagementsystem.service.PersonalInformationService;
import com.forestchiefmanagementsystem.util.R;
import com.forestchiefmanagementsystem.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
@ResponseBody
@Slf4j
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    PersonalInformationService personalInformationService;

    /**
     * 登录 成功后将账号id写入session中
     * @param httpSession
     * @param user
     * @param password
     * @return 账号信息
     */
    @GetMapping("/login")
   // 登录方法
    public R<PersonalInformation> login(HttpSession httpSession ,String user , String password){
        // 根据用户名查询账户
        Account one = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getUser, user));
        // 判断账户是否存在
        if (one==null)return R.error("无该账户");
        // 判断密码是否正确
        PersonalInformation pi;
        if (one.getPassword().equals(password)){
            // 根据id查询个人信息
            PersonalInformation pI = personalInformationService.getOne(new LambdaQueryWrapper<PersonalInformation>().eq(PersonalInformation::getId, one.getPersonalInformationId()));
            // 设置密码为null
            one.setPassword("null");
            // 设置session
            httpSession.setAttribute("id",pI.getId());
            httpSession.setAttribute("position",pI.getPosition());
            // 设置个人信息
            pi=pI;
        }else {
            // 密码错误
            return R.error("密码错误");
        }
        // 返回个人信息
        return R.success(pi);
    }

    /**
     * 登出
     * @param httpSession
     * @return
     */
   @GetMapping("/login_out")
    public R<String> loginOut(HttpSession httpSession){
        //从session中移除id属性
        httpSession.removeAttribute("id");
        //返回登出成功信息
        return R.success("登出成功");
    }

    /**
     * 修改账号
     * @param map
     * @return
     */
    @PutMapping
    public R<String> modifyAccount(@RequestBody Map<String,Object> map){
//    public R<String> modifyAccount(String order_password,String new_password ,String new_user){

        //获取旧密码
        String order_password= (String) map.get("order_password");

        //获取新密码
        String new_password=(String) map.get("new_password");
        //获取新用户名
        String new_user=(String) map.get("new_user");

        //获取当前用户信息
        Account one = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getPersonalInformationId, ThreadUtil.getPIId()));

        //判断订单密码是否正确
        if (!one.getPassword().equals(order_password)){
            return R.error("密码错误");
        }

        //判断新用户名是否已存在
        List<Account> list = accountService.list(new LambdaQueryWrapper<Account>().eq(Account::getUser, new_user));
        if (list.size()!=0){
            return R.error("已有改账户名");
        }
        //创建新账户
        Account account = new Account();
        account.setId(one.getId());
        account.setUser(new_user);
        account.setPassword(new_password);
        //更新新账户信息
        boolean b = accountService.updateById(account);
        if (b){
            return R.success("修改账号成功");
        }else {
            return R.error("修改账户失败");
        }
    }

}