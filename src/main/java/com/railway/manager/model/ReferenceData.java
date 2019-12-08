package com.railway.manager.model;

import apple.laf.JRSUIUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway-manager
 * @description: 参照数据表
 * @author: lijiwen
 * @create: 2019-11-23 21:26
 **/
@ApiModel("参照数据")
@Data
@Accessors(chain = true)
public class ReferenceData {
/*    id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `reference_max_value` varchar(255) DEFAULT NULL COMMENT '最大值（电机最大承受电流、）',
  `normal_value` varchar(255) DEFAULT NULL COMMENT '正常值（电机额定电流、）',
  `true_value` varchar(255) DEFAULT NULL COMMENT '实时（电机实时电流、）',
  `max_value` varchar(255) DEFAULT NULL COMMENT '最大（电机产生峰值电流、）',
  `average_value` varchar(255) DEFAULT NULL COMMENT '平均值（电机平均电流、）',
  `max_value_time` timestamp NULL DEFAULT NULL COMMENT '最大值发生时间',*/
    /**
     *id
    **/
    @ApiModelProperty(name = "id")
    private Integer id;
    /**
     *最大参考（电机最大承受电流、电机稳定温度、）
    **/
    @ApiModelProperty(name = "")
    private String reference_max_value;
    /**
     *正常值（电机额定电流、电机工作温度、电机实时温度）
    **/
    @ApiModelProperty(name = "")
    private String normal_value;
    /**
     *实时（电机实时电流、）
    **/
    @ApiModelProperty(name = "")
    private String true_value;
    /**
     *最大（电机产生峰值电流、）
    **/
    @ApiModelProperty(name = "")
    private String max_value;
    /**
     *平均值（电机平均电流、）
    **/
    @ApiModelProperty(name = "")
    private String average_value;
    /**
     *最大值发生时间
    **/
    @ApiModelProperty(name = "")
    private String max_value_time;
    /**
     *创建时间
    **/
    private String created_at;
    /**
     *修改时间
    **/
    private String update_at;
}
