package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.Enum.ConstantEnum;
import com.railway.manager.entity.SysDictAdd;
import com.railway.manager.model.SysDict;
import com.railway.manager.service.system.SysDictService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.SysDictVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加字典数据", notes = "添加字典数据")
    public Map<String, Object> addDict(SysDictAdd sysDict) {
        return sysDictService.add(sysDict);
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "修改字典数据", notes = "根据id修改字典数据，仅可修改字典名称或状态")
    public Map<String, Object> updateDict(@RequestParam Integer id,
            @ApiParam("字典名称") @RequestParam String dictName,
            @ApiParam("状态编码：1启用，0禁止") @RequestParam Integer status) {

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();

        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("idEqual", id.intValue());

        int countCondition = sysDictService.selectCount(conditionMap);
        if(countCondition < 1) {
            resultMap.put("code", "201");
            resultMap.put("description", "根据id查不到字典数据，禁止更新");

            return resultMap;
        } else if(countCondition > 1) {
            resultMap.put("code", "202");
            resultMap.put("description", "根据id查到多条字典数据，禁止更新");

            return resultMap;
        }

        //检查字典名称是否为空
        if(StringUtils.isBlank(dictName)) {
            resultMap.put("code", "301");
            resultMap.put("description", "字典名称不能为空");

            return resultMap;
        }

        //检查状态是否正确
        if(status.intValue()!= ConstantEnum.status_no
                && status.intValue()!=ConstantEnum.status_yes) {
            resultMap.put("code", "302");
            resultMap.put("description", "状态编码不符合要求");

            return resultMap;
        }

        SysDict dict = new SysDict();
        dict.setId(id);
        dict.setDictName(dictName.trim());
        dict.setStatus(status);
        dict.setUpdateTime(new Date());

        int count = sysDictService.update(dict);

        resultMap = ResultMapUtil.getUpdateResult(count);
        return resultMap;
    }

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有字典数据", notes = "查询所有字典数据")
    public Map<String, Object> dictList(@ApiParam("关键字") @RequestParam(required = false) String advanceColumn,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        Map<String, Object> conditionMap = Maps.newHashMap();

        if(!StringUtils.isBlank(advanceColumn)) {
            conditionMap.put("advanceColumnLike", "%"+advanceColumn.trim()+"%");
        }

        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }

        conditionMap.put("_limit", pageSize);
        conditionMap.put("_offset", (pageNum.intValue() - 1) * pageSize.intValue());

        int allCount = sysDictService.selectCount(conditionMap);
        List<SysDictVo> dictVoList = sysDictService.getList(conditionMap);

        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", dictVoList.size());
        resultMap.put("dictVoList", dictVoList);

        return resultMap;
    }

    @PostMapping("/getListByDictType")
    @ResponseBody
    @ApiOperation(value = "根据字典类型查询符合要求的字典数据", notes = "根据字典类型查询符合要求的字典数据")
    public Map<String, Object> getListByDictType(@ApiParam("字典类型") @RequestParam String dictType) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();

        conditionMap.put("dictTypeEqual", dictType);

        List<SysDictVo> dictVoList = sysDictService.getList(conditionMap);
        int allCount = sysDictService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", dictVoList.size());
        resultMap.put("dictVoList", dictVoList);

        return resultMap;
    }
}
