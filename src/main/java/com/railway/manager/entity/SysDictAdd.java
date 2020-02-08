package com.railway.manager.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 字典表add
 * @author: chenglin
 * @create: 2019-12-05 23:15
 **/
@Data
@ApiModel("字典表add")
@Accessors(chain = true)
public class SysDictAdd implements Serializable {

    @ApiModelProperty("字典类型")
    private String dictType;
    @ApiModelProperty("字典编码")
    private Integer dictCode;
    @ApiModelProperty("字典名称")
    private String dictName;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
}