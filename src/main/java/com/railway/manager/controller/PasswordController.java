package com.railway.manager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 找回密码服务
 * @author: chenglin
 * @create: 2019-12-04 16:48
 * @version 1.0
 **/
@Api(tags = "找回密码服务接口", value = "找回密码服务接口")
@Controller
@RequestMapping("/api/pwd")
public class PasswordController {

    @PostMapping("/resetPwd")
    @ResponseBody
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public Map<String, Object> resetPassword(@RequestParam String phoneNumber,@RequestParam String imageCode,
                                             @RequestParam String phoneCode, @RequestParam String password,
                                             @RequestParam(required = false) String pwdConfirm) {

        return new HashMap<String, Object>();
    }

    @PostMapping("/sendPhoneCode")
    @ResponseBody
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    public Map<String, Object> sendPhoneCode(@RequestParam String phoneNumber) {

        return new HashMap<String, Object>();
    }
}
