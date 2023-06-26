package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 个人信息
 * @TableName personal_information
 */
@TableName(value ="personal_information")
@Data
public class PersonalInformation implements Serializable {
    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 名字
     */
    private String username;

    /**
     * 性别                        
     */
    private Integer sex;

    /**
     * 图片名称                      
     */
    private String personalImg;

    /**
     * 林地 ID                     
     */
    private String woodsId;

    /**
     * 职位      0管理员，1林长，2监管员，3巡护员
     */
    private Integer position;

    /**
     * 入职时间                      
     */
    private Date entryTime;

    /**
     * 状态      0空闲，1工作中          
     */
    private Integer status;

    /**
     * 电话号码                      
     */
    private String phoneNumber;

    /**
     * 上级 id   个人信息 id           
     */
    private String superiorId;

    /**
     * 逻辑删除 0存在，1删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}