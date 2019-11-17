package com.railway.manager.controller;

import com.railway.manager.model.HeartData;
import com.railway.manager.service.HeartDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 心跳信息
 * @author: lijiwen
 * @create: 2019-11-16 21:29
 **/
@Api(tags = "心跳信息查询",value = "心跳信息查询")
@Controller
@RequestMapping("/api/heart")
public class HeartDataController {
    @Autowired
    private HeartDataService heartDataService;
    @GetMapping("getHeartData")
    @ResponseBody
    public List<HeartData> getHeartData(@RequestParam(required = false) String deviceId){
        return heartDataService.getHeartData(deviceId);
    }
}
