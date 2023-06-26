package com.forestchiefmanagementsystem.mapper;

import com.forestchiefmanagementsystem.pojo.Inform;
import com.forestchiefmanagementsystem.pojo.Patrol;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forestchiefmanagementsystem.pojo.dto.PatrolDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author hm
* @description 针对表【patrol(巡护)】的数据库操作Mapper
* @createDate 2023-05-14 15:37:31
* @Entity com.forestchiefmanagementsystem.pojo.Patrol
*/
@Mapper
public interface PatrolMapper extends BaseMapper<Patrol> {
    public List<PatrolDto> getPatrolPage(@Param("idList") List idList);
}




