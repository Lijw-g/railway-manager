package com.railway.manager.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.railway.manager.model.MonitorData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: railway-manager
 * @description: yujing
 * @author: lijiwen
 * @create: 2019-12-29 22:26
 **/
@Api(tags = "获取预警信息", value = "获取预警信息")
@Controller
@RequestMapping("/api/earlyWarring")
public class EarlyWarningController {

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "获取列表", notes = "获取预警列表")
    public Map<String, Object> getListUser(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("count", 1);
        List<MonitorData> monitorDatas = Lists.newArrayList();
        MonitorData monitorData = new MonitorData();
        monitorData.setLine("2");
        monitorData.setCityCode("3");
        monitorData.setDeviceId("234");
        monitorData.setdAstate("1");
        monitorData.setDegree("32");
        monitorData.setdVstate("33");
        monitorDatas.add(monitorData);
        result.put("data", monitorDatas);
        return result;
    }
    @GetMapping("/haveNewWarring")
    @ResponseBody
    @ApiOperation(value = "最新报警", notes = "最新报警")
    public Map<String, Object> haveNewWarring(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("count", 1);
        List<MonitorData> monitorDatas = Lists.newArrayList();
        MonitorData monitorData = new MonitorData();
        monitorData.setLine("5");
        monitorData.setCityCode("8");
        monitorData.setDeviceId("4");
        monitorData.setdAstate("1");
        monitorData.setDegree("32");
        monitorData.setdVstate("33");
        monitorDatas.add(monitorData);
        result.put("data", monitorDatas);
        return result;
    }

}
