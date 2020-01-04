package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway_manager
 * @description: 角色权限配置
 * @author: chenglin
 * @create: 2020-01-04 16:30
 **/
@Data
@Accessors(chain = true)
@ApiModel("角色用户配置")
public class RolePermission {

    private Integer id;

    @ApiModelProperty("角色代码")
    private String roleCode;
    @ApiModelProperty("权限id")
    private Integer permissionId;
}