package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ApiModel(value = "预警信息", description = "预警信息")
public class EarlyWarring {

    @ApiModelProperty(value = "id")
    private long id;
    @ApiModelProperty("城市")
    private String cityCode;
    @ApiModelProperty("车次")
    private String shift;
    @ApiModelProperty("线路")
    private String line;
    @ApiModelProperty(value = "车门号")
    private String doorNo;
    @ApiModelProperty("门控器电压")
    private String mVstate;
    @ApiModelProperty("门控器电流")
    private String mAstate;
    @ApiModelProperty("门控器温度")
    private String mTstate;
    @ApiModelProperty("门电机电压")
    private String dVstate;
    @ApiModelProperty("门电机电流")
    private String dAstate;
    @ApiModelProperty("门电机温度")
    private String dTstate;
    @ApiModelProperty("开合度")
    private String degree;
    @ApiModelProperty("校验码")
    private String crcCode;
    @ApiModelProperty("创建时间")
    private String createdAt;
}
