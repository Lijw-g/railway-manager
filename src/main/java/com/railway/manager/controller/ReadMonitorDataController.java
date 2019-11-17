package com.railway.manager.controller;

import com.railway.manager.model.MonitorData;
import com.railway.manager.service.ReadMonitorDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 读取数据信息
 * @author: lijiwen
 * @create: 2019-11-11 20:20
 **/
@Api(tags = "读取核心数据信息接口", value = "读取核心数据信息接口")
@Controller
@RequestMapping("/api/MonitorData")
public class ReadMonitorDataController {
    @Autowired
    private ReadMonitorDataService readMonitorDataService;

    @GetMapping("/getMvState")
    @ResponseBody
    @ApiOperation(value = "查询门控电压信息", notes = "查询门控电压信息")
    public List<String> listMvState() {
        return null;
    }

    @GetMapping("/getMAState")
    @ResponseBody
    @ApiOperation(value = "查询门控电流信息", notes = "查询门控电流信息")
    public List<String> listMAState() {
        return null;
    }

    @GetMapping("/getMTState")
    @ResponseBody
    @ApiOperation(value = "查询门控温度", notes = "查询门控温度")
    public List<String> listMTState() {
        return null;
    }

    @GetMapping("/getDVState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电压信息", notes = "查询电机输入电压信息")
    public List<String> listDVState() {
        return null;
    }

    @GetMapping("/getDAState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电流信息", notes = "查询电机输入电流信息")
    public List<String> listDAState() {
        return null;
    }

    @GetMapping("/getDTState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电流信息", notes = "查询电机输入电流信息")
    public List<String> listDTState() {
        return null;
    }

    @GetMapping("/getDegree")
    @ResponseBody
    @ApiOperation(value = "门开合度", notes = "门开合度")
    public List<String> listDegree() {
        return null;
    }

    @GetMapping("/listAllData")
    @ResponseBody
    @ApiOperation(value = "所有数据信息", notes = "所有数据信息")
    public List<MonitorData> listAllData(
            @ApiParam(type = "String", value = "厂家")
            @RequestParam(defaultValue = "") String factory,
            @ApiParam(type = "String", value = "城市")
            @RequestParam(defaultValue = "") String cityId,
            @ApiParam(type = "String", value = "线路")
            @RequestParam(defaultValue = "") String line,
            @ApiParam(type = "String", value = "状态")
            @RequestParam(defaultValue = "") String status
    ) {
        return readMonitorDataService.listAllData(factory, cityId, line, status);
    }


}
