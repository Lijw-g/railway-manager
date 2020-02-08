package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 轨道交通车辆型号库vo
 * @author: chenglin
 * @create: 2019-12-29 20:46
 **/
@Data
@ApiModel("轨道交通车辆型号库vo")
@Accessors(chain = true)
public class SubwayCarLibVo {

    private Integer id;

    @ApiModelProperty("投放城市编码")
    private Integer cityCode;
    @ApiModelProperty("投放城市名称")
    private String cityName;
    @ApiModelProperty("地铁车辆型号编码")
    private Integer subwayCarTypeCode;
    @ApiModelProperty("地铁车辆型号")
    private String subwayCarType;
    @ApiModelProperty("门数量")
    private Integer doorQuantity;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("生产厂家")
    private String manufacturer;
    @ApiModelProperty("投放时间")
    private Date marketTime;
    @ApiModelProperty("投放线路编码")
    private Integer lineCode;
    @ApiModelProperty("投放线路名称")
    private String lineName;
    @ApiModelProperty("备注")
    private String comment;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
}