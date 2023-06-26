package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标注实例
 * @TableName label_example
 */
@TableName(value ="label_example")
@Data
public class LabelExample implements Serializable {
    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 标注id
     */
    private String labelId;

    /**
     * 标注实例状态  0待完成，1已完成
     */
    private Integer exampleStatus;

    /**
     * 标注内容
     */
    private String exampleContent;

    /**
     * 实例图片组    限制5张图片
     */
    private String exampleImgs;

    /**
     * 标注人id
     */
    private String labelPersonalId;

    /**
     * 处理人id
     */
    private String dealWithPersonId;


    /**
     * 处理结果              
     */
    private String dealWithContent;

    /**
     * 处理图片      限制5张图片
     */
    private String dealWithImgs;

    /**
     * 处理时间              
     */
    private Date dealWithTime;

    /**
     * geojson
     */
    private String labelGeojson;

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