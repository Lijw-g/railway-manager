package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.entity.SubwayCarLibAdd;
import com.railway.manager.model.FailureMeasureLib;
import com.railway.manager.model.SubwayCarLib;
import com.railway.manager.service.system.FailureMeasureLibService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.FailureMeasureLibVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @program: railway_manager
 * @description: 故障措施库接口
 * @author: chenglin
 * @create: 2019-12-29 10:18
 * @version 1.0
 **/
@Api(tags = "故障措施库接口", value = "故障措施库接口")
@Controller
@RequestMapping("/api/failureMeasureLib")
public class FailureMeasureLibController {

    @Autowired
    private FailureMeasureLibService failureMeasureLibService;

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加故障措施库", notes = "添加故障措施库")
    public Map<String, Object> addFailureMeasureLib(FailureMeasureLib failureMeasureLib) {

        int count = failureMeasureLibService.add(failureMeasureLib);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);

        return resultMap;
    }

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "故障措施库查询", notes = "故障措施库查询")
    public Map<String, Object> failureMeasureLibList(@ApiParam("关键字") @RequestParam(required = false) String advanceColumn,
                                     @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        if(!StringUtils.isBlank(advanceColumn)) {
            conditionMap.put("advanceColumnLike", "%"+advanceColumn.trim()+"%");
        }

        if( pageNum<1) {
            pageNum = 1;
        }
        if( pageSize<1) {
            pageSize = 10;
        }

        if(pageNum!=null && pageSize!=null) {
            conditionMap.put("_limit", pageSize);
            conditionMap.put("_offset", (pageNum.intValue()-1)*pageSize.intValue());
        }

        List<FailureMeasureLibVo> failureMeasureLibVoList = failureMeasureLibService.getList(conditionMap);
        int allCount = failureMeasureLibService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", failureMeasureLibVoList.size());
        resultMap.put("failureMeasureLibVoList", failureMeasureLibVoList);

        return resultMap;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除故障措施库数据", notes = "根据id删除故障措施库数据")
    public Map<String, Object> deleteFailureMeasureLib(@RequestParam Integer id) {
        return failureMeasureLibService.delete(id);
    }

    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改故障措施库", notes = "根据id修改故障措施库")
    public Map<String, Object> editFailureMeasureLib(FailureMeasureLib failureMeasureLib) {

        //返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(failureMeasureLib==null || failureMeasureLib.getId()==null) {
            resultMap.put("code", "201");
            resultMap.put("description", "id不能为空");

            return resultMap;
        }

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", failureMeasureLib.getId().intValue());

        if(failureMeasureLibService.selectCount(conditionMap) < 1) {
            resultMap.put("code", "202");
            resultMap.put("description", "根据id查不到数据，禁止更新");

            return resultMap;
        }

        failureMeasureLib.setUpdateTime(new Date());

        int count = failureMeasureLibService.editSubwayCarLib(failureMeasureLib);
        //返回结果
        resultMap = ResultMapUtil.getUpdateResult(count);
        return resultMap;
    }
}
