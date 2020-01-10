package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 车门控制器vo
 * @author: chenglin
 * @create: 2019-12-29 17:46
 * @version 1.0
 **/
@Data
@ApiModel("车门控制器表vo")
@Accessors(chain = true)
public class DoorPlcVo {

    private Integer id;

    @ApiModelProperty("门控器型号编码")
    private Integer doorControllerTypeCode;
    @ApiModelProperty("门控器型号")
    private String doorControllerType;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("生产厂家")
    private String manufacturer;
    @ApiModelProperty("生产日期")
    private Date productionDate;
    @ApiModelProperty("控制类型编码")
    private Integer controllerTypeCode;
    @ApiModelProperty("控制类型")
    private String controllerType;
    @ApiModelProperty("电机类型编码")
    private Integer motorTypeCode;
    @ApiModelProperty("电机类型")
    private String motorType;
    @ApiModelProperty("额定电压")
    private String ratedVoltage;
    @ApiModelProperty("额定电流")
    private String ratedCurrent;
    @ApiModelProperty("稳态温度")
    private String stableTemperature;
    @ApiModelProperty("电机额定电压")
    private String motorRatedVoltage;
    @ApiModelProperty("电机额定电流")
    private String motorRatedCurrent;
    @ApiModelProperty("电机稳态温度")
    private String motorStableTemperature;
    @ApiModelProperty("门开度范围")
    private String gatage;
    @ApiModelProperty("最大门开度")
    private String maxGatage;
    @ApiModelProperty("门开启时间")
    private String doorStartTime;
    @ApiModelProperty("门关闭时间")
    private String doorStopTime;
    @ApiModelProperty("备注")
    private String comment;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}