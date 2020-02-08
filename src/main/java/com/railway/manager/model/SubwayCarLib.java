package com.railway.manager.model;

import com.railway.manager.entity.SubwayCarLibAdd;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 轨道交通车辆型号库
 * @author: chenglin
 * @create: 2019-12-29 20:46
 **/
@Data
@ApiModel("轨道交通车辆型号库")
@Accessors(chain = true)
public class SubwayCarLib {

    public SubwayCarLib() {

    }

    public SubwayCarLib(SubwayCarLibAdd subwayCarLibAdd) {
        this.cityCode = subwayCarLibAdd.getCityCode();
        this.subwayCarTypeCode = subwayCarLibAdd.getSubwayCarTypeCode();
        this.doorQuantity = subwayCarLibAdd.getDoorQuantity();
        this.manufacturerCode = subwayCarLibAdd.getManufacturerCode();
        this.marketTime = subwayCarLibAdd.getMarketTime();
        this.lineCode = subwayCarLibAdd.getLineCode();
        this.comment = subwayCarLibAdd.getComment();
    }


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
    @ApiModelProperty("更新人")
    private String updateUser;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}