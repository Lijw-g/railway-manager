package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.service.system.UserService;
import com.railway.manager.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String, Object> getListUser(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String name) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        int count = userService.getCount(name);
        List<User> list = userService.getList(pageNum, pageSize, name);
        Map<String, Object> result = Maps.newHashMap();
        result.put("count", count);
        result.put("users", list);
        return result;
    }

    @PostMapping("add")
    @ResponseBody
    public int addUser(User user) {
        return userService.add(user);
    }

    @PostMapping("edit")
    @ResponseBody
    public int editUser(User user) {
        return userService.editUser(user);
    }


}
