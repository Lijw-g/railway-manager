package com.railway.manager.vo;

import com.railway.manager.model.ReferenceData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 核心数据返回
 * @author: lijiwen
 * @create: 2019-12-08 20:24
 **/
@ApiModel("核心数据返回")
public class CoreDataVo {
    /**
     * 核心数据
     **/
    @ApiModelProperty(name = "核心数据")
    private List<String> coreData;
    /**
     * 参考数据
     **/
    @ApiModelProperty("参考数据")
    private ReferenceData referenceData;

    private int count;

    public List<String> getCoreData() {
        return coreData;
    }

    public void setCoreData(List<String> coreData) {
        this.coreData = coreData;
    }

    public ReferenceData getReferenceData() {
        return referenceData;
    }

    public void setReferenceData(ReferenceData referenceData) {
        this.referenceData = referenceData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
