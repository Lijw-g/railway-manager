package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 角色
 * @author: lijiwen
 * @create: 2019-09-15 13:24
 **/
@Data
@Accessors(chain = true)
@ApiModel("角色信息")
public class Role {
    @ApiModelProperty("角色名字")
    private String roleName;
    private String roleCode;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
