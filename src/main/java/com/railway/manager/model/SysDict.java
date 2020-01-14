package com.railway.manager.model;

import com.railway.manager.entity.SysDictAdd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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

    public SysDict() {

    }

    public SysDict(SysDictAdd sysDictAdd) {
        this.dictType = sysDictAdd.getDictType().trim();
        this.dictCode = sysDictAdd.getDictCode();
        this.dictName = sysDictAdd.getDictName().trim();
        this.status = sysDictAdd.getStatus();
    }

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
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}