package com.railway.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/***
 * @author: Lijiwen
 * Description:监控数据model
 * @createDate
 **/
@Accessors(chain = true)
@ApiModel(value = "核心数据实体类", description = "核心数据实体类")
public class MonitorData {
    @ApiModelProperty(value = "id")
    private long id;
    @JsonProperty("协议")
    @ApiModelProperty("协议")
    private String agreement;
    @JsonProperty("数据长度")
    @ApiModelProperty("长度")
    private long length;
    @JsonProperty("设备id")
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    @JsonProperty("命令")
    @ApiModelProperty(value = "命令")
    private String command;
    @JsonProperty("门控器电压")
    @ApiModelProperty("门控器电压")
    private String mVstate;
    @JsonProperty("门控器电流")
    @ApiModelProperty("门控器电流")
    private String mAstate;
    @JsonProperty("门控器温度")
    @ApiModelProperty("门控器温度")
    private String mTstate;
    @JsonProperty("门电机电压")
    @ApiModelProperty("门电机电压")
    private String dVstate;
    @JsonProperty("门电机电流")
    @ApiModelProperty("门电机电流")
    private String dAstate;
    @JsonProperty("门电机温度")
    @ApiModelProperty("门电机温度")
    private String dTstate;
    @JsonProperty("开合度")
    @ApiModelProperty("开合度")
    private String degree;
    @ApiModelProperty("校验码")
    private String crcCode;
    @JsonProperty("创建时间")
    @ApiModelProperty("创建时间")
    private String createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getmVstate() {
        return mVstate;
    }

    public void setmVstate(String mVstate) {
        this.mVstate = mVstate;
    }

    public String getmAstate() {
        return mAstate;
    }

    public void setmAstate(String mAstate) {
        this.mAstate = mAstate;
    }

    public String getmTstate() {
        return mTstate;
    }

    public void setmTstate(String mTstate) {
        this.mTstate = mTstate;
    }

    public String getdVstate() {
        return dVstate;
    }

    public void setdVstate(String dVstate) {
        this.dVstate = dVstate;
    }

    public String getdAstate() {
        return dAstate;
    }

    public void setdAstate(String dAstate) {
        this.dAstate = dAstate;
    }

    public String getdTstate() {
        return dTstate;
    }

    public void setdTstate(String dTstate) {
        this.dTstate = dTstate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(String crcCode) {
        this.crcCode = crcCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
