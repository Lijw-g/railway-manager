package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.model.FailureMeasureLib;
import com.railway.manager.service.system.FailureMeasureLibService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.FailureMeasureLibVo;
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
    public Map<String, Object> failureMeasureLibList(@RequestParam(required = false) String advanceColumn,
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
}
