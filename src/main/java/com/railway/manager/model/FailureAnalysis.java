package com.railway.manager.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 故障分析类
 * @author: chenglin
 * @create: 2019-12-04 20:46
 **/
@Data
@ApiModel("故障分析结果表")
@Accessors(chain = true)
public class FailureAnalysis {

    private Integer id;

    @ApiModelProperty("城市编码")
    private Integer cityCode;
    @ApiModelProperty("地铁车辆编码")
    private Integer subwayCarCode;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("生产批次编码")
    private Integer productionBatchCode;
    @ApiModelProperty("运行线路")
    private Integer lineCode;
    @ApiModelProperty("故障原因")
    private String failureCause;
    @ApiModelProperty("分析结果")
    private String analysisResult;
    @ApiModelProperty("发生时间")
    private Date happeningTime;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
}