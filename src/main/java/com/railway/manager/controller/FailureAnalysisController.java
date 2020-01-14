package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.entity.UserAdd;
import com.railway.manager.model.FailureAnalysis;
import com.railway.manager.service.system.FailureAnalysisService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.FailureAnalysisVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * @version 1.1
 * @program: railway_manager
 * @description: 故障分析结果接口
 * @author: chenglin
 * @create: 2019-12-04 22:18
 **/
@Api(tags = "故障分析结果接口", value = "故障分析结果接口")
@Controller
@RequestMapping("/api/failureAnalysis")
public class FailureAnalysisController {

    @Autowired
    private FailureAnalysisService failureAnalysisService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
        //initDataBinder(request, binder);
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加故障分析结果", notes = "添加故障分析结果")
    public Map<String, Object> addFailureAnalysis(FailureAnalysis failureAnalysis) {

        int count = failureAnalysisService.add(failureAnalysis);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);

        return resultMap;
    }

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "故障分析结果查询", notes = "故障分析结果查询")
    public Map<String, Object> failureAnalysisList(@RequestParam(required = false) String advanceColumn,
                                                   @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        Map<String, Object> conditionMap = Maps.newHashMap();
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

        List<FailureAnalysisVo> failureAnalysisVoList = failureAnalysisService.getList(conditionMap);
        int allCount = failureAnalysisService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", failureAnalysisVoList.size());
        resultMap.put("failureAnalysisVoList", failureAnalysisVoList);

        return resultMap;
    }

    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "更改故障分析结果", notes = "更改故障分析结果")
    public int editUser(FailureAnalysis failureAnalysis) {
        return failureAnalysisService.edit(failureAnalysis);
    }


    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除故障分析结果", notes = "删除故障分析结果")
    public Integer delete(String id) {
        return failureAnalysisService.delete(id);
    }

}
