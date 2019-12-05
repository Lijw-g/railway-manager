package com.railway.manager.entity;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway_manager
 * @description: 字典表
 * @author: chenglin
 * @create: 2019-12-05 23:15
 * @version 1.0
 **/
@Data
@ApiModel("字典表")
@Accessors(chain = true)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("字典类型")
    private String dictType;
    @ApiModelProperty("字典编码")
    private Integer dictCode;
    @ApiModelProperty("字典名称")
    private String dictName;
    @ApiModelProperty("描述")
    private String description;
    /**
     * 状态（1启用 0不启用）
     */
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}

