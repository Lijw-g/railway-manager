package com.railway.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway-monitor
 * @description: 地理位置实体类
 * @author: lijiwen
 * @create: 2019-10-27 19:47
 **/
@Data
@Accessors(chain = true)
@ApiModel("地理位置信息")
public class GeographicalData {
    @ApiModelProperty("日期")
    private String date;
    @ApiModelProperty("时间")
    private String time;
    @ApiModelProperty("经度")
    private String lat;
    @ApiModelProperty("纬度")
    private String lng;
    @ApiModelProperty("速度")
    private String speed;
    @ApiModelProperty("方向")
    private String direction;
    @ApiModelProperty("标志")
    private String sign;

}
