package com.forestchiefmanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forestchiefmanagementsystem.mapper.AccountMapper;
import com.forestchiefmanagementsystem.pojo.Account;
import com.forestchiefmanagementsystem.service.AccountService;
import org.springframework.stereotype.Service;

/**
* @author hm
* @description 针对表【account(账号)】的数据库操作Service实现
* @createDate 2023-05-14 15:37:30
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

}




