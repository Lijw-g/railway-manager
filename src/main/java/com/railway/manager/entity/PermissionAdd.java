package com.railway.manager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway_manager
 * @description: 添加权限信息类
 * @author: chenglin
 * @create: 2020-01-04 22:00
 **/
@Data
@Accessors(chain = true)
@ApiModel("添加权限信息类")
public class PermissionAdd {

    @ApiModelProperty("父id")
    private Integer parentId;
    @ApiModelProperty("权限名称")
    private String name;
    @ApiModelProperty("路径")
    private String url;
    @ApiModelProperty("0目录 1菜单 2按钮")
    private Integer menuTypeCode;
    @ApiModelProperty("权限标识")
    private String perms;
    @ApiModelProperty("菜单排序")
    private Integer sortNo;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
}