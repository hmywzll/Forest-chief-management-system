package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 巡护
 * @TableName patrol
 */
@TableName(value ="patrol")
@Data
public class Patrol implements Serializable {
    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 巡护人id
     */
    private String patrolPersonId;

    /**
     * 结束时间                
     */
    private Date endTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 巡护时长                
     */
    private Double patrolDuration;

    /**
     * 巡护里程                
     */
    private Double patrolMileage;

    /**
     * 巡护报告                
     */
    private String patrolContent;

    /**
     * 巡护图片组  限制5张图片 
     */
    private String patrolImgs;

    /**
     * 巡护状态
     */
    private Integer patrolStatus;

    /**
     * 巡护区域
     */
    private String patrolArea;

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