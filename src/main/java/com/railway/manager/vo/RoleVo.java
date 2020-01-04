package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 角色vo
 * @author: chenglin
 * @create: 2020-01-04 10:00
 **/
@Data
@Accessors(chain = true)
@ApiModel("角色信息vo")
public class RoleVo {

    @ApiModelProperty("角色编码")
    private String roleCode;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("状态名称")
    private String statusName;
    @ApiModelProperty("创建时间")
    private Date createdTime;
}
