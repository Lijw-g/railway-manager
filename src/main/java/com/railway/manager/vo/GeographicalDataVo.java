package com.railway.manager.vo;

import com.railway.manager.model.GeographicalData;
import lombok.Data;

import java.util.List;

@Data
public class GeographicalDataVo {
    private List<GeographicalData> geographicalData;
    private Integer count;
}
