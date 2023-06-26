package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 任务
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
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
     * 任务类型      0普通，1事件    
     */
    private Integer taskType;

    /**
     * 事件id                 
     */
    private String labelId;

    /**
     * 任务期限                 
     */
    private Date taskDeadline;

    /**
     * 任务状态      0待完成，1已完成 
     */
    private Integer taskStatus;

    /**
     * 任务内容                 
     */
    private String taskContent;

    /**
     * 接收人                  
     */
    private String receptionPersonId;

    /**
     * 任务报告                 
     */
    private String taskReport;

    /**
     * 任务图片组    限制5张图片      
     */
    private String taskImgs;

    /**
     * 任务完成时间               
     */
    private Date taskCompletion;

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