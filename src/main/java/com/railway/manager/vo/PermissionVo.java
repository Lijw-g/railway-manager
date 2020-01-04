package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @program: railway_manager
 * @description: 权限vo
 * @author: chenglin
 * @create: 2020-01-04 16:00
 **/
@Data
@Accessors(chain = true)
@ApiModel("权限信息vo")
public class PermissionVo {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("父id")
    private Integer parentId;
    @ApiModelProperty("权限名称")
    private String name;
    @ApiModelProperty("路径")
    private String url;
    @ApiModelProperty("0目录 1菜单 2按钮权限")
    private Integer menuTypeCode;
    @ApiModelProperty("权限菜单类型")
    private String menuTypeName;
    @ApiModelProperty("权限标识")
    private String perms;
    @ApiModelProperty("菜单顺序")
    private Integer sortNo;
    @ApiModelProperty("是否叶子节点: 1:是 0:不是")
    private Integer isLeaf;
    @ApiModelProperty("是否叶子节点: 1:是 0:不是")
    private String isLeafVal;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("状态（1启用 0不启用）")
    private String statusName;

    private List<PermissionVo> permissionList;
}