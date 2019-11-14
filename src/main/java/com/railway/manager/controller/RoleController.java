package com.railway.manager.controller;

import com.railway.manager.model.Role;
import com.railway.manager.model.User;
import com.railway.manager.service.system.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有角色", notes = "查询方法")
    public List<Role> listRole() {
        return userRoleService.listRole();
    }


    @PostMapping("add")
    @ApiOperation(value = "新增角色", notes = "新增方法")
    @ResponseBody
    public int addRole(Role role) {
        return userRoleService.add(role);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @ResponseBody
    public int editRole(Role role) {
        return userRoleService.editUser(role);
    }

    @DeleteMapping("del")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ResponseBody
    public  int delRole(Role role){
        return userRoleService.delRole(role);
    }

}
