package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 字典表vo
 * @author: chenglin
 * @create: 2019-12-05 23:15
 **/
@Data
@ApiModel("字典表vo")
@Accessors(chain = true)
public class SysDictVo implements Serializable {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("字典类型")
    private String dictType;
    @ApiModelProperty("字典编码")
    private Integer dictCode;
    @ApiModelProperty("字典名称")
    private String dictName;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("状态名称")
    private String statusName;
}