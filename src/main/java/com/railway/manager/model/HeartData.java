package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
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
    private String ip;
    private String deviceId;
    private String stateCode;
}
