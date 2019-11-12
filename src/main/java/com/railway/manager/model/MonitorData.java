package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/***
 * @author: Lijiwen
 * Description:监控数据model
 * @createDate
 **/
@Data
@Accessors(chain = true)
@ApiModel("核心数据实体类")
public class MonitorData {

    private long id;
    @ApiModelProperty("协议")
    private String agreement;
    private long length;
    private String deviceId;
    private String command;
    private String mVstate;
    private String mAstate;
    private String mTstate;
    private String dVstate;
    private String dAstate;
    private String dTstate;
    private String degree;
    private String crcCode;
    private Timestamp created_at;
}
