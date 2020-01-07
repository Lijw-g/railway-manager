package com.railway.manager.controller;

import com.railway.manager.Enum.ConstantEnum;
import com.railway.manager.entity.PermissionAdd;
import com.railway.manager.model.Permission;
import com.railway.manager.service.system.PermissionService;
import com.railway.manager.utils.ResultMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: railway-manager
 * @description: 权限信息接口
 * @author: chenglin
 * @create: 2020-01-04 21:00
 * @version 1.0
 **/
@Api(tags = "权限信息接口", value = "权限信息接口")
@Controller
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有权限信息", notes = "查询所有权限信息")
    public Map<String, Object> permissionList() {

        return permissionService.getPermissionResult();
    }

    @PostMapping("/getlistByCode")
    @ResponseBody
    @ApiOperation(value = "根据角色编码获取权限信息", notes = "roleCode：角色编码")
    public Map<String, Object> getlistByCode(@RequestParam String roleCode) {

        return permissionService.getlistByCode(roleCode);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加权限信息", notes = "添加权限信息")
    @ResponseBody
    public Map<String, Object> addPermission(PermissionAdd permission) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //检查菜单类型
        if(permission.getMenuTypeCode() == null) {
            resultMap.put("code", "201");
            resultMap.put("description", "菜单类型不能为空");

            return resultMap;
        }
        if((permission.getMenuTypeCode()!=ConstantEnum.menuType_dir)
                && (permission.getMenuTypeCode()!=ConstantEnum.menuType_menu)
                && (permission.getMenuTypeCode()!=ConstantEnum.menuType_btn)) {
            resultMap.put("code", "202");
            resultMap.put("description", "菜单类型不正确");

            return resultMap;
        }

        //检查父id是否为空
        if(permission.getParentId()==null) {

            //父id为空时，菜单类型必为目录
            if(permission.getMenuTypeCode() != ConstantEnum.menuType_dir) {
                resultMap.put("code", "203");
                resultMap.put("description", "父id为空时，菜单类型只可选择目录");

                return resultMap;
            }
        } else {

            if(permission.getMenuTypeCode() == ConstantEnum.menuType_dir) {
                resultMap.put("code", "204");
                resultMap.put("description", "父id不为空时，菜单类型不可选择目录");

                return resultMap;
            }

            //检查父id是否存在于权限表
            Map<String, Object> conditionMap = new HashMap<String, Object>();
            conditionMap.put("idEqual", permission.getParentId().intValue());

            int idCount = permissionService.selectCount(conditionMap);

            if(idCount < 1) {
                resultMap.put("code", "205");
                resultMap.put("description", "父id对应权限不存在");

                return resultMap;
            } else if(idCount > 1) {
                resultMap.put("code", "206");
                resultMap.put("description", "父id对应多条权限数据，请检查");

                return resultMap;
            }
        }

        Permission permissionToAdd = new Permission(permission);
        int count = permissionService.add(permissionToAdd);

        resultMap = ResultMapUtil.getAddResult(count);

        return resultMap;
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改权限信息", notes = "修改权限信息, id不允许修改")
    @ResponseBody
    public Map<String, Object> editPermission(@RequestParam Integer id, PermissionAdd permission) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //检查id
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", id);
        if (permissionService.selectCount(conditionMap) < 1) {
            resultMap.put("code", "201");
            resultMap.put("description", "根据id查不到相关权限信息，更新失败");

            return resultMap;
        }

        //检查菜单类型
        if(permission.getMenuTypeCode() == null) {
            resultMap.put("code", "202");
            resultMap.put("description", "菜单类型不能为空");

            return resultMap;
        }
        if((permission.getMenuTypeCode()!=ConstantEnum.menuType_dir)
                && (permission.getMenuTypeCode()!=ConstantEnum.menuType_menu)
                && (permission.getMenuTypeCode()!=ConstantEnum.menuType_btn)) {
            resultMap.put("code", "203");
            resultMap.put("description", "菜单类型不正确");

            return resultMap;
        }

        Permission permissionToUpdate = new Permission(permission);
        permissionToUpdate.setId(id);
        permissionToUpdate.setUpdatedTime(new Date());

        int count = permissionService.update(permissionToUpdate);

        resultMap = ResultMapUtil.getUpdateResult(count);
        return resultMap;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除权限信息", notes = "删除权限信息")
    @ResponseBody
    public Map<String, Object> deletePermission(@RequestParam Integer id) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //如果当前id已是其他权限的父id，则不允许删除
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", id);
        if (permissionService.selectCount(conditionMap) < 1) {
            resultMap.put("code", "201");
            resultMap.put("description", "根据id查不到相关权限信息");

            return resultMap;
        }

        conditionMap.clear();
        conditionMap.put("parentIdEqual", id);
        if(permissionService.selectCount(conditionMap) > 0) {
            resultMap.put("code", "202");
            resultMap.put("description", "当前权限id作为其他权限父id，不允许删除");

            return resultMap;
        }

        int count = permissionService.delete(id);

        //返回结果
        resultMap = ResultMapUtil.getDeleteResult(count);

        return resultMap;
    }
}