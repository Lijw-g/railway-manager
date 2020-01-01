package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.entity.SysDict;
import com.railway.manager.model.Role;
import com.railway.manager.service.system.SysDictService;
import com.railway.manager.vo.FailureAnalysisVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
@RequestMapping("/api/dict")
public class SysDicController {
    @Resource
    private SysDictService sysDictService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有字典", notes = "查询方法")
    public  Map<String, Object> listRole(
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
        int count = sysDictService.selectCount(conditionMap);
        Map<String, Object> result = Maps.newHashMap();
        result.put("count", count);
        result.put("sysDic", sysDictService.getList(conditionMap));
        return result;
    }

    @PostMapping("/getListByDictType")
    @ResponseBody
    @ApiOperation(value = "根据字典类型查询符合要求的字典数据", notes = "根据字典类型查询符合要求的字典数据")
    public Map<String, Object> getListByDictType(@RequestParam String dictType) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();

        conditionMap.put("dictTypeEqual", dictType);

        List<SysDict> sysDictList = sysDictService.getList(conditionMap);
        int allCount = sysDictService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", sysDictList.size());
        resultMap.put("sysDictList", sysDictList);

        return resultMap;
    }
}
