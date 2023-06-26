package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 通知
 * @TableName inform
 */
@TableName(value ="inform")
@Data
public class Inform implements Serializable {
    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 发起人
     */
    private String sendPersonId;

    /**
     * 通知标题  限制60字                    
     */
    private String informTitle;

    /**
     * 通知内容                           
     */
    private String informContent;

    /**
     * 林地id    通知范围                   
     */
    private String woodsId;

    /**
     * 职位      通知范围，0管理员，1林长，2监管员，3巡护员
     */
    private Integer position;

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