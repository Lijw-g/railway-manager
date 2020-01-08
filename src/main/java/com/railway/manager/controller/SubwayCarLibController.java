package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.model.SubwayCarLib;
import com.railway.manager.service.system.SubwayCarLibService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.SubwayCarLibVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 轨道交通车辆型号库接口
 * @author: chenglin
 * @create: 2019-12-29 11:22
 **/
@Api(tags = "轨道交通车辆型号库接口", value = "轨道交通车辆型号库接口")
@Controller
@RequestMapping("/api/subwayCarLib")
public class SubwayCarLibController {

    @Autowired
    private SubwayCarLibService subwayCarLibService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
        //initDataBinder(request, binder);
    }

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "轨道交通车辆型号库查询", notes = "轨道交通车辆型号库查询")
    public Map<String, Object> subwayCarLibList(@RequestParam(required = false) String advanceColumn,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        if (!StringUtils.isBlank(advanceColumn)) {
            conditionMap.put("advanceColumnLike", "%" + advanceColumn.trim() + "%");
        }

        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }

        if (pageNum != null && pageSize != null) {
            conditionMap.put("_limit", pageSize);
            conditionMap.put("_offset", (pageNum.intValue() - 1) * pageSize.intValue());
        }

        List<SubwayCarLibVo> subwayCarLibVoList = subwayCarLibService.getList(conditionMap);
        int allCount = subwayCarLibService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", subwayCarLibVoList.size());
        resultMap.put("subwayCarLibVoList", subwayCarLibVoList);

        return resultMap;
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加轨道交通车辆型号库", notes = "添加轨道交通车辆型号库")
    public Map<String, Object> addSubwayCarLib(SubwayCarLib subwayCarLib) {
        int count = subwayCarLibService.add(subwayCarLib);
        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);
        return resultMap;
    }

    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改轨道交通车辆型号库", notes = "修改轨道交通车辆型号库")
    public Map<String, Object> editSubwayCarLib(SubwayCarLib subwayCarLib) {
        int count = subwayCarLibService.editSubwayCarLib(subwayCarLib);
        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);
        return resultMap;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除轨道交通车辆型号库", notes = "删除轨道交通车辆型号库")
    public Map<String, Object> delete(String id) {
        int count = subwayCarLibService.delete(id);
        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);
        return resultMap;
    }
}
