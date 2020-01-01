package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.model.DoorPlc;
import com.railway.manager.service.system.DoorPlcService;
import com.railway.manager.utils.ResultMapUtil;
import com.railway.manager.vo.DoorPlcVo;
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
 * @description: 车门控制器数据接口
 * @author: chenglin
 * @create: 2019-12-29 16:19
 * @version 1.0
 **/
@Api(tags = "车门控制器数据接口", value = "车门控制器数据接口")
@Controller
@RequestMapping("/api/doorPlc")
public class DoorPlcController {

    @Autowired
    private DoorPlcService doorPlcService;

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
    @ApiOperation(value = "添加车门控制器数据", notes = "添加车门控制器数据")
    public Map<String, Object> addDoorPlc(DoorPlc doorPlc) {

        int count = doorPlcService.add(doorPlc);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);

        return resultMap;
    }

    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除车门控制器数据", notes = "删除车门控制器数据")
    public Map<String, Object> deleteDoorPlc(Integer id) {

        int count = doorPlcService.delete(id);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getDeleteResult(count);

        return resultMap;
    }

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "车门控制器数据查询", notes = "车门控制器数据查询")
    public Map<String, Object> doorPlcList(@RequestParam(required = false) String advanceColumn,
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

        List<DoorPlcVo> doorPlcVoList = doorPlcService.getList(conditionMap);
        int allCount = doorPlcService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", doorPlcVoList.size());
        resultMap.put("doorPlcVoList", doorPlcVoList);

        return resultMap;
    }

    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "更新车门控制器数据", notes = "更新车门控制器数据")
    public Map<String, Object> updateDoorPlc(DoorPlc doorPlc) {

        int count = doorPlcService.update(doorPlc);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getUpdateResult(count);

        return resultMap;
    }
}
