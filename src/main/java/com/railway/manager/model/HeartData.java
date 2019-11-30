package com.railway.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: railway-monitor
 * @description: 心跳数据
 * @author: lijiwen
 * @create: 2019-10-27 13:37
 **/
@Data
@ApiModel("心跳信息")
public class HeartData {
    private Integer id;
    @ApiModelProperty("ip地址")
    private String ip;
    @ApiModelProperty("设备id")
    private String deviceId;
    @ApiModelProperty("设备状态码")
    private String stateCode;
}
