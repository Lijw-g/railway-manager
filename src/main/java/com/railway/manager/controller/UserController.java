package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.entity.UserAdd;
import com.railway.manager.service.system.UserService;
import com.railway.manager.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: user info
 * @author: lijiwen
 * @create: 2019-09-09 20:32
 **/
@Api(tags = "用户信息接口", value = "用户信息接口")
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有用户", notes = "查询方法")
    public Map<String, Object> getListUser (
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false,defaultValue = "") String advanceColumn) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        int count = userService.getCount(advanceColumn);
        List<UserVo> userList = userService.getList(pageNum, pageSize, advanceColumn);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", count);
        resultMap.put("currentCount", userList.size());
        resultMap.put("userList", userList);

        return resultMap;
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    public Map<String, Object> addUser(UserAdd user) {
        return userService.add(user);
    }

    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public Map<String, Object> editUser(UserAdd user) {
        return userService.editUser(user);
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    public Map<String, Object> resetPassword(@RequestParam String userName,@RequestParam String oldPassword,
                                             @RequestParam String newPassword,@RequestParam String pwdConfirm) {
        return userService.resetPassword(userName, oldPassword, newPassword, pwdConfirm);
    }
}
