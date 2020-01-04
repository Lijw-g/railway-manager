package com.railway.manager.model;

import com.railway.manager.entity.UserAdd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway_manager
 * @description: 角色用户配置
 * @author: chenglin
 * @create: 2020-01-04 12:00
 **/
@Data
@Accessors(chain = true)
@ApiModel("角色用户配置")
public class RoleUser {

    public RoleUser() {

    }

    public RoleUser(UserAdd userAdd) {
        this.roleCode = userAdd.getRoleCode().trim();
        this.userName = userAdd.getUserName().trim();
    }

    private Integer id;

    @ApiModelProperty("角色代码")
    private String roleCode;
    @ApiModelProperty("用户名")
    private String userName;
}
