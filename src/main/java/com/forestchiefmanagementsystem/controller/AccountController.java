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
    public R<PersonalInformation> login(HttpSession httpSession ,String user , String password){
        Account one = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getUser, user));
        if (one==null)return R.error("无该账户");
        PersonalInformation pi;
        if (one.getPassword().equals(password)){
            PersonalInformation pI = personalInformationService.getOne(new LambdaQueryWrapper<PersonalInformation>().eq(PersonalInformation::getId, one.getPersonalInformationId()));
            one.setPassword("null");
            httpSession.setAttribute("id",pI.getId());
            httpSession.setAttribute("position",pI.getPosition());
            pi=pI;
        }else {
            return R.error("密码错误");
        }
        return R.success(pi);
    }

    /**
     * 登出
     * @param httpSession
     * @return
     */
    @GetMapping("/login_out")
    public R<String> loginOut(HttpSession httpSession){
        httpSession.removeAttribute("id");
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

        String order_password= (String) map.get("order_password");

        String new_password=(String) map.get("new_password");
        String new_user=(String) map.get("new_user");

        Account one = accountService.getOne(new LambdaQueryWrapper<Account>().eq(Account::getPersonalInformationId, ThreadUtil.getPIId()));

        if (!one.getPassword().equals(order_password)){
            return R.error("密码错误");
        }

        List<Account> list = accountService.list(new LambdaQueryWrapper<Account>().eq(Account::getUser, new_user));
        if (list.size()!=0){
            return R.error("已有改账户名");
        }
        Account account = new Account();
        account.setId(one.getId());
        account.setUser(new_user);
        account.setPassword(new_password);
        boolean b = accountService.updateById(account);
        if (b){
            return R.success("修改账号成功");
        }else {
            return R.error("修改账户失败");
        }
    }

}
