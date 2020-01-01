package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 轨道交通车辆型号库
 * @author: chenglin
 * @create: 2019-12-29 20:46
 * @version 1.0
 **/
@Data
@ApiModel("轨道交通车辆型号库")
@Accessors(chain = true)
public class SubwayCarLib {

    private Integer id;

    @ApiModelProperty("投放城市编码")
    private Integer cityCode;
    @ApiModelProperty("地铁车辆型号编码")
    private Integer subwayCarTypeCode;
    @ApiModelProperty("门数量")
    private Integer doorQuantity;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("投放时间")
    private Date marketTime;
    @ApiModelProperty("投放线路")
    private Integer lineCode;
    @ApiModelProperty("备注")
    private String comment;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
}