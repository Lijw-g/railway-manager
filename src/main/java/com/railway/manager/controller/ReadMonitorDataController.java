package com.railway.manager.controller;

import com.railway.manager.model.excel.MonitorData;
import com.railway.manager.service.ReadMonitorDataService;
import com.railway.manager.utils.ExcelUtiles;
import com.railway.manager.vo.CoreDataVo;
import com.railway.manager.vo.OperationConditionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
    public CoreDataVo listMvState(
            @ApiParam("厂家")
            @RequestParam(defaultValue = "") String factory,
            @ApiParam("城市")
            @RequestParam(defaultValue = "") String city,
            @ApiParam("线路")
            @RequestParam(defaultValue = "") String line,
            @ApiParam("运行情况")
            @RequestParam(defaultValue = "") String situation) {
        return readMonitorDataService.listMvState(factory, city, line, situation);
    }

    @GetMapping("/getMAState")
    @ResponseBody
    @ApiOperation(value = "查询门控电流信息", notes = "查询门控电流信息")
    public CoreDataVo listMAState(
            @ApiParam("厂家")
            @RequestParam(defaultValue = "") String factory,
            @ApiParam("城市")
            @RequestParam(defaultValue = "") String city,
            @ApiParam("线路")
            @RequestParam(defaultValue = "") String line,
            @ApiParam("运行情况")
            @RequestParam(defaultValue = "") String situation) {
        return readMonitorDataService.listMAState(factory, city, line, situation);
    }

    @GetMapping("/getMTState")
    @ResponseBody
    @ApiOperation(value = "查询门控温度", notes = "查询门控温度")
    public CoreDataVo listMTState(
            @ApiParam("厂家")
            @RequestParam(defaultValue = "") String factory,
            @ApiParam("城市")
            @RequestParam(defaultValue = "") String city,
            @ApiParam("线路")
            @RequestParam(defaultValue = "") String line,
            @ApiParam("运行情况")
            @RequestParam(defaultValue = "") String situation
    ) {
        return readMonitorDataService.listMTState(factory, city, line, situation);
    }

    @GetMapping("/getDVState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电压信息", notes = "查询电机输入电压信息")
    public CoreDataVo listDVState(@ApiParam("厂家")
                                  @RequestParam(defaultValue = "") String factory,
                                  @ApiParam("城市")
                                  @RequestParam(defaultValue = "") String city,
                                  @ApiParam("线路")
                                  @RequestParam(defaultValue = "") String line,
                                  @ApiParam("运行情况")
                                  @RequestParam(defaultValue = "") String situation) {
        return readMonitorDataService.listDVState(factory, city, line, situation);
    }

    @GetMapping("/getDAState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电流信息", notes = "查询电机输入电流信息")
    public CoreDataVo listDAState(@ApiParam("厂家")
                                  @RequestParam(defaultValue = "") String factory,
                                  @ApiParam("城市")
                                  @RequestParam(defaultValue = "") String city,
                                  @ApiParam("线路")
                                  @RequestParam(defaultValue = "") String line,
                                  @ApiParam("运行情况")
                                  @RequestParam(defaultValue = "") String situation) {
        return readMonitorDataService.listDAState(factory, city, line, situation);
    }

    @GetMapping("/getDTState")
    @ResponseBody
    @ApiOperation(value = "查询电机温度信息", notes = "查询电机温度信息")
    public CoreDataVo listDTState(@ApiParam("厂家")
                                  @RequestParam(defaultValue = "") String factory,
                                  @ApiParam("城市")
                                  @RequestParam(defaultValue = "") String city,
                                  @ApiParam("线路")
                                  @RequestParam(defaultValue = "") String line,
                                  @ApiParam("运行情况")
                                  @RequestParam(defaultValue = "") String situation) {
        return readMonitorDataService.listDTState(factory, city, line, situation);
    }

    @GetMapping("/getDegree")
    @ResponseBody
    @ApiOperation(value = "门开合度", notes = "门开合度")
    public CoreDataVo listDegree(@ApiParam("厂家")
                                 @RequestParam(defaultValue = "") String factory,
                                 @ApiParam("城市")
                                 @RequestParam(defaultValue = "") String city,
                                 @ApiParam("线路")
                                 @RequestParam(defaultValue = "") String line,
                                 @ApiParam("运行情况")
                                 @RequestParam(defaultValue = "") String situation) {
        return readMonitorDataService.listDegree(factory, city, line, situation);
    }

    @GetMapping("/listAllData")
    @ResponseBody
    @ApiOperation(value = "车辆运行情况和获取所有数据", notes = "车辆运行情况和获取所有数据")
    public OperationConditionVo listAllData(
            @ApiParam("厂家")
            @RequestParam(defaultValue = "") String factory,
            @ApiParam("城市")
            @RequestParam(defaultValue = "") String city,
            @ApiParam("线路")
            @RequestParam(defaultValue = "") String line,
            @ApiParam("运行情况")
            @RequestParam(defaultValue = "") String situation,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String beginTime,
            @RequestParam(required = false, defaultValue = "") String enTime) {
        OperationConditionVo operationConditionVo = new OperationConditionVo();
        operationConditionVo.setData(readMonitorDataService.listAllData(factory, city, line, situation, pageNum, pageSize, beginTime, enTime));
        operationConditionVo.setCount(readMonitorDataService.getCount(factory, city, line, situation, pageNum, pageSize, beginTime, enTime));
        return operationConditionVo;
    }

    @GetMapping(value = "export")
    @ResponseBody
    @ApiOperation(value = "导出数据", notes = "导出数据")
    public void export(HttpServletResponse response) {
        //模拟从数据库获取需要导出的数据
        List<MonitorData> monitorDataList = readMonitorDataService.listAllData();
        //导出操作
        ExcelUtiles.exportExcel(monitorDataList, "数据统计表", "数据统计表", MonitorData.class, "数据统计.xls", response);
    }

}
