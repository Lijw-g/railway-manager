package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 故障措施库vo
 * @author: chenglin
 * @create: 2019-12-29 15:46
 **/
@Data
@ApiModel("故障措施库前端vo")
@Accessors(chain = true)
public class FailureMeasureLibVo {

    private Integer id;

    @ApiModelProperty("地铁车辆型号编码")
    private Integer subwayCarTypeCode;
    @ApiModelProperty("地铁车辆型号")
    private String subwayCarType;
    @ApiModelProperty("地铁车门型号编码")
    private Integer subwayDoorTypeCode;
    @ApiModelProperty("地铁车门型号")
    private String subwayDoorType;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("生产厂家")
    private String manufacturer;
    @ApiModelProperty("传感器型号编码")
    private Integer sensorTypeCode;
    @ApiModelProperty("传感器型号")
    private String sensorType;
    @ApiModelProperty("故障原因（常见问题）")
    private String failureCause;
    @ApiModelProperty("解决措施")
    private String solution;
    @ApiModelProperty("备注")
    private String comment;
    @ApiModelProperty("操作")
    private String operation;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
}