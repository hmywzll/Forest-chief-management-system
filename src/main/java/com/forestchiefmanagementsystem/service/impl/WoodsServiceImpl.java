package com.forestchiefmanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forestchiefmanagementsystem.pojo.Woods;
import com.forestchiefmanagementsystem.service.WoodsService;
import com.forestchiefmanagementsystem.mapper.WoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author hm
* @description 针对表【woods(林地)】的数据库操作Service实现
* @createDate 2023-05-14 15:37:31
*/
@Service
public class WoodsServiceImpl extends ServiceImpl<WoodsMapper, Woods>
    implements WoodsService{

}




