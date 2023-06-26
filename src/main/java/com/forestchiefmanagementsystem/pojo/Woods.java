package com.forestchiefmanagementsystem.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 林地
 * @TableName woods
 */
@TableName(value ="woods")
@Data
public class Woods implements Serializable {
    /**
     * ID
     */
    @TableId
    private String id;

    /**
     * 林地名称
     */
    private String woodsName;

    /**
     * 林地编号
     */
    private String woodsNumber;

    /**
     * 林地geojson
     */
    private String woodsGeojson;

    /**
     * 逻辑删除
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