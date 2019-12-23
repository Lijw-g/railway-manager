package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.entity.SysDict;
import com.railway.manager.model.Role;
import com.railway.manager.service.system.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: railway-manager
 * @description: 数据字典
 * @author: lijiwen
 * @create: 2019-12-23 17:33
 **/
@Api(tags = "数据字典", value = "数据字字典")
@Controller
@RequestMapping("/api/dic")
public class SysDicController {
    @Resource
    private SysDictService sysDictService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有角色", notes = "查询方法")
    public List<SysDict> listRole(
            @ApiParam("厂家名称")
            @RequestParam(defaultValue = "") String factoryName,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> conditionMap = Maps.newHashMap();
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        conditionMap.put("_offset", (pageNum.intValue() - 1) * pageSize.intValue());
        conditionMap.put("_limit", pageSize);
        conditionMap.put("dictName", factoryName);


        return sysDictService.getList(conditionMap);
    }
}
