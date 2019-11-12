package com.railway.manager.controller;

import com.railway.manager.model.Role;
import com.railway.manager.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: railway-manager
 * @description: 角色信息
 * @author: lijiwen
 * @create: 2019-11-12 18:55
 **/
@Api(tags = "角色信息接口", value = "角色信息接口")
@Controller
@RequestMapping("/api/role")
public class RoleController {
    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有用户", notes = "查询方法")
    public List<Role> getListUser() {
        return null;
    }
}
