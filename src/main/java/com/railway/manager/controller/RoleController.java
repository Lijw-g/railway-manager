package com.railway.manager.controller;

import com.railway.manager.model.Role;
import com.railway.manager.service.system.UserRoleService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway-manager
 * @description: 角色信息
 * @author: chenglin
 * @create: 2019-11-12 18:55
 * @version 2.0
 **/
@Api(tags = "角色信息接口", value = "角色信息接口")
@Controller
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有角色信息", notes = "查询所有角色信息")
    public Map<String, Object> roleList(@RequestParam(value="页码",required = false,defaultValue = "1") Integer pageNum,
                               @RequestParam(value="单页数据量",required = false,defaultValue = "10") Integer pageSize,
                               @RequestParam(value="查询条件",required = false) String advanceColumn) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        if( pageNum<1) {
            pageNum = 1;
            conditionMap.put("_limit", pageSize);
        }
        if( pageSize<1) {
            pageSize = 10;
            conditionMap.put("_offset", (pageNum.intValue()-1)*pageSize.intValue());
        }
        if(!StringUtils.isBlank(advanceColumn)) {
            conditionMap.put("advanceColumnLike", "%"+advanceColumn.trim()+"%");
        }

        int allCount = userRoleService.selectCount(conditionMap);

        List<RoleVo> roleVoList = userRoleService.getList(conditionMap);

        //返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", roleVoList.size());
        resultMap.put("roleVoList", roleVoList);

        return resultMap;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加角色信息", notes = "添加角色信息，角色编码要求唯一")
    @ResponseBody
    public Map<String, Object> addRole(@RequestParam(value="角色编码") String roleCode, @RequestParam(value="角色名称") String roleName) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //判断参数不能为空
        if(StringUtils.isBlank(roleCode)) {
            resultMap.put("code", "201");
            resultMap.put("description", "角色编码不能为空");
            return resultMap;
        }
        if(StringUtils.isBlank(roleName)) {
            resultMap.put("code", "202");
            resultMap.put("description", "角色名称不能为空");
            return resultMap;
        }
        roleCode = roleCode.trim();
        roleName = roleName.trim();

        //判断角色编码是否唯一
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("roleCodeEqual", roleCode);
        if(userRoleService.selectCount(conditionMap) > 0) {
            resultMap.put("code", "203");
            resultMap.put("description", "角色编码已存在");
            return resultMap;
        }

        Role role = new Role();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);

        int count = userRoleService.add(role);

        resultMap = ResultMapUtil.getAddResult(count);

        return resultMap;
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改角色信息", notes = "仅可修改角色名称，角色编码不可修改")
    @ResponseBody
    public Map<String, Object> editRole(@RequestParam(value="角色编码") String roleCode, @RequestParam(value="角色名称") String roleName) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //判断参数不能为空
        if(StringUtils.isBlank(roleCode)) {
            resultMap.put("code", "201");
            resultMap.put("description", "角色编码不能为空");
            return resultMap;
        }
        if(StringUtils.isBlank(roleName)) {
            resultMap.put("code", "202");
            resultMap.put("description", "角色名称不能为空");
            return resultMap;
        }
        roleCode = roleCode.trim();
        roleName = roleName.trim();

        //判断角色编码是否唯一
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("roleCodeEqual", roleCode);
        int countNum = userRoleService.selectCount(conditionMap);
        if(countNum < 1) {
            resultMap.put("code", "203");
            resultMap.put("description", "角色不存在，禁止修改");
            return resultMap;
        } else if(countNum > 1) {
            resultMap.put("code", "204");
            resultMap.put("description", "角色编码对应多个角色数据，禁止修改");
            return resultMap;
        }

        Role role = new Role();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        role.setUpdatedTime(new Date());

        int count = userRoleService.editRole(role);

        resultMap = ResultMapUtil.getUpdateResult(count);
        return resultMap;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息")
    @ResponseBody
    public Map<String, Object> deleteRote(@RequestParam(value="角色编码") String roleCode){

        int count = userRoleService.deleteRole(roleCode);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getDeleteResult(count);

        return resultMap;
    }
}