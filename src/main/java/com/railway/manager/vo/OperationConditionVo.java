package com.railway.manager.vo;

import com.railway.manager.model.MonitorData;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("车辆运行情况和获取所有数据")
public class OperationConditionVo {
    private List<MonitorData> data;
    private int count;

    public List<MonitorData> getData() {
        return data;
    }

    public void setData(List<MonitorData> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
