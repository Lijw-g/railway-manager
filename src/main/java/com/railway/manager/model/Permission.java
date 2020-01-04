package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 权限
 * @author: chenglin
 * @create: 2020-01-04 16:00
 **/
@Data
@Accessors(chain = true)
@ApiModel("权限信息")
public class Permission {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("父id")
    private Integer parentId;
    @ApiModelProperty("权限名称")
    private String name;
    @ApiModelProperty("路径")
    private String url;
    @ApiModelProperty("组件")
    private String component;
    @ApiModelProperty("组件名字")
    private String componentName;
    @ApiModelProperty("0目录 1菜单 2按钮权限")
    private Integer menuTypeCode;
    @ApiModelProperty("权限标识")
    private String perms;
    @ApiModelProperty("菜单排序")
    private Integer sortNo;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("是否叶子节点: 1:是 0:不是")
    private Integer isLeaf;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;

    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createdTime;
    @ApiModelProperty("修改人")
    private String updateUser;
    @ApiModelProperty("修改时间")
    private Date updatedTime;
}