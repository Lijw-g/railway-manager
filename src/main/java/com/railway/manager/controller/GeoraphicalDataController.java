package com.railway.manager.controller;

import com.railway.manager.model.GeographicalData;
import com.railway.manager.service.GeoraphicalDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 地理位置信息
 * @author: lijiwen
 * @create: 2019-11-16 21:50
 **/
@Api(tags = "地理位置信息查询",value = "地理位置信息查询")
@Controller
@RequestMapping("api/geo")
public class GeoraphicalDataController {
    @Autowired
    private GeoraphicalDataService georaphicalDataService;
    @GetMapping("GeoraphicalData")
    @ResponseBody
    public List<GeographicalData> getGeprahicalData(){
        return georaphicalDataService.GeoraphicalData();
    }
}
