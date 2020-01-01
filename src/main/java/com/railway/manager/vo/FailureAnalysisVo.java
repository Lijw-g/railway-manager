package com.railway.manager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 故障分析类vo
 * @author: chenglin
 * @create: 2019-12-04 20:46
 * @version 1.0
 **/
@Data
@ApiModel("故障分析结果表vo")
@Accessors(chain = true)
public class FailureAnalysisVo {

    private Integer id;

    @ApiModelProperty("城市编码")
    private Integer cityCode;
    @ApiModelProperty("城市名称")
    private Integer cityName;
    @ApiModelProperty("地铁车辆编码")
    private Integer subwayCarCode;
    @ApiModelProperty("地铁车辆名称")
    private Integer subwayCarName;
    @ApiModelProperty("生产厂家编码")
    private Integer manufacturerCode;
    @ApiModelProperty("生产厂家")
    private Integer manufacturer;
    @ApiModelProperty("生产批次编码")
    private Integer productionBatchCode;
    @ApiModelProperty("生产批次")
    private Integer productionBatch;
    @ApiModelProperty("运行线路")
    private Integer lineCode;
    @ApiModelProperty("运行线路名称")
    private Integer lineName;
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