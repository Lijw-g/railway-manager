package com.railway.manager.model;

import com.railway.manager.entity.DoorPlcAdd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 车门控制器
 * @author: chenglin
 * @create: 2019-12-29 17:46
 **/
@Data
@ApiModel("车门控制器表")
@Accessors(chain = true)
public class DoorPlc {

    public DoorPlc() {

    }

    public DoorPlc(DoorPlcAdd doorPlcAdd) {
        this.doorControllerTypeCode = doorPlcAdd.getDoorControllerTypeCode();
        this.manufacturerCode = doorPlcAdd.getManufacturerCode();
        this.productionDate = doorPlcAdd.getProductionDate();
        this.controllerTypeCode = doorPlcAdd.getControllerTypeCode();
        this.motorTypeCode = doorPlcAdd.getMotorTypeCode();
        this.ratedVoltage = doorPlcAdd.getRatedVoltage();
        this.ratedCurrent = doorPlcAdd.getRatedCurrent();
        this.stableTemperature = doorPlcAdd.getStableTemperature();
        this.motorRatedVoltage = doorPlcAdd.getMotorRatedVoltage();
        this.motorRatedCurrent = doorPlcAdd.getMotorRatedCurrent();
        this.motorStableTemperature = doorPlcAdd.getMotorStableTemperature();
        this.gatage = doorPlcAdd.getGatage();
        this.maxGatage = doorPlcAdd.getMaxGatage();
        this.doorStartTime = doorPlcAdd.getDoorStartTime();
        this.doorStopTime = doorPlcAdd.getDoorStopTime();
    }

    private Integer id;

    @ApiModelProperty("门控器型号编码")
    private Integer doorControllerTypeCode;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("生产日期")
    private Date productionDate;
    @ApiModelProperty("控制类型编码")
    private Integer controllerTypeCode;
    @ApiModelProperty("电机类型编码")
    private Integer motorTypeCode;
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
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;
}