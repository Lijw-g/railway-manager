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
    @ApiModelProperty("菜单标题")
    private String name;
    @ApiModelProperty("路径")
    private String url;
    @ApiModelProperty("组件")
    private String component;
    @ApiModelProperty("组件名字")
    private String componentName;
    @ApiModelProperty("一级菜单跳转地址")
    private String redirect;
    @ApiModelProperty("0一级菜单 1子菜单 2按钮权限")
    private Integer menuTypeCode;
    @ApiModelProperty("菜单权限编码")
    private String perms;
    @ApiModelProperty("权限策略1显示2禁用")
    private Integer permsTypeCode;
    @ApiModelProperty("菜单排序")
    private Integer sortNo;
    @ApiModelProperty("聚合子路由: 1是0否")
    private Integer alwaysShow;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("是否路由菜单: 0:不是 1:是（默认值1）")
    private Integer isRoute;
    @ApiModelProperty("是否叶子节点: 1:是 0:不是")
    private Integer isLeaf;
    @ApiModelProperty("是否隐藏路由: 0否,1是")
    private Integer hidden;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("删除状态 0正常 1已删除")
    private Integer delFlag;
    @ApiModelProperty("是否添加数据权限1是0否")
    private Integer ruleFlag;
    @ApiModelProperty("按钮权限状态 0无效 1有效")
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