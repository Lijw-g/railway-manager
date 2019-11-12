package com.railway.manager.controller;

import com.railway.manager.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 读取数据信息
 * @author: lijiwen
 * @create: 2019-11-11 20:20
 **/
@Api(tags = "读取核心数据信息接口",value = "读取核心数据信息接口")
@Controller
@RequestMapping("/api/MonotorData")
public class ReadMonitorDataController {
    @GetMapping("/getMvState")
    @ResponseBody
    @ApiOperation(value = "查询门控电压信息", notes = "查询门控电压信息")
    public List<String> getMvState() {
        return null;
    }

    @GetMapping("/getMAState")
    @ResponseBody
    @ApiOperation(value = "查询门控电流信息", notes = "查询门控电流信息")
    public List<String> getMAState() {
        return null;
    }

    @GetMapping("/getMTState")
    @ResponseBody
    @ApiOperation(value = "查询门控温度", notes = "查询门控温度")
    public List<String> getMTState() {
        return null;
    }

    @GetMapping("/getDVState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电压信息", notes = "查询电机输入电压信息")
    public List<String> getDVState() {
        return null;
    }
    @GetMapping("/getDAState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电流信息", notes = "查询电机输入电流信息")
    public List<String> getDAState() {
        return null;
    }

    @GetMapping("/getDTState")
    @ResponseBody
    @ApiOperation(value = "查询电机输入电流信息", notes = "查询电机输入电流信息")
    public List<String> getDTState() {
        return null;
    }
    @GetMapping("/getDegree")
    @ResponseBody
    @ApiOperation(value = "门开合度", notes = "门开合度")
    public List<String> getDegree() {
        return null;
    }

}
