package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标注
 * @TableName label
 */
@TableName(value ="label")
@Data
public class Label implements Serializable {
    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 标注名称
     */
    private String labelName;

    /**
     * 标注图标               
     */
    private String labelImg;

    /**
     * 类型      0普通标注，1事件标注
     */
    private Integer labelType;

    /**
     * 描述                 
     */
    private String labelRemark;

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