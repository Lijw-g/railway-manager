package com.railway.manager.model.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @description: 用户类
 * Created by chenglin on 2019/12/3.
 **/
@Data
@ApiModel("登录日志表")
@Accessors(chain = true)
public class LoginLog {

    private Integer id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("展示名")
    private String displayName;
    @ApiModelProperty("ip")
    private String ip;
    @ApiModelProperty("设备")
    private String device;
    @ApiModelProperty("设备类型")
    private String deviceType;
    @ApiModelProperty("浏览器")
    private String browser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @ApiModelProperty("登录时间")
    private Date loginTime;
}
