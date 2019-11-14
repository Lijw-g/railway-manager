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
    @ApiModelProperty("角色代码")
    private String roleCode;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createdTime;
    @ApiModelProperty("修改人")
    private String updateUser;
    @ApiModelProperty("修改时间")
    private Date updatedTime;
}
