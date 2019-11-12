package com.railway.manager.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: railway_manager
 * @description: udp读取设备信息
 * @author: lijiwen
 * @create: 2019-10-27 22:59
 **/
@Api(tags = "读取设备信息相关接口",value = "读取设备信息相关接口")

@Controller
@RequestMapping("api/readInfo")
public class ReadDeviceInfo {

    //读取固定门的时间间隔7112  应答 7212

    //读取采集终端编号 7111    应答 7211

    //设置采集终端编码 7113    应答  7211
}
