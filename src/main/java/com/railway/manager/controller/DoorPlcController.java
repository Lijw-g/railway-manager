package com.railway.manager.controller;

import com.google.common.collect.Maps;
import com.railway.manager.entity.DoorPlcAdd;
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
 * @version 1.1
 **/
@Api(tags = "车门控制器数据接口", value = "车门控制器数据接口")
@Controller
@RequestMapping("/api/doorPlc")
public class DoorPlcController {

    @Autowired
    private DoorPlcService doorPlcService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
        //initDataBinder(request, binder);
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加车门控制器数据", notes = "添加车门控制器数据")
    public Map<String, Object> addDoorPlc(DoorPlcAdd doorPlc) {

        DoorPlc dp = new DoorPlc(doorPlc);

        int count = doorPlcService.add(dp);

        //返回结果
        Map<String, Object> resultMap = ResultMapUtil.getAddResult(count);

        return resultMap;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除车门控制器数据", notes = "根据id删除车门控制器数据")
    public Map<String, Object> deleteDoorPlc(@RequestParam Integer id) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", id.intValue());

        //返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(doorPlcService.selectCount(conditionMap) < 1) {
            resultMap.put("code", "201");
            resultMap.put("description", "根据id查不到数据，禁止删除");

            return resultMap;
        }

        int count = doorPlcService.delete(id);

        //返回结果
        resultMap = ResultMapUtil.getDeleteResult(count);

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
    @ApiOperation(value = "更新车门控制器数据", notes = "根据id更新车门控制器数据")
    public Map<String, Object> updateDoorPlc(@RequestParam Integer id, DoorPlcAdd doorPlc) {

        //返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("idEqual", id.intValue());

        if(doorPlcService.selectCount(conditionMap) < 1) {
            resultMap.put("code", "201");
            resultMap.put("description", "根据id查不到数据，禁止更新");

            return resultMap;
        }

        DoorPlc dp = new DoorPlc(doorPlc);
        dp.setId(id);
        dp.setUpdateTime(new Date());

        int count = doorPlcService.update(dp);

        //返回结果
        resultMap = ResultMapUtil.getUpdateResult(count);

        return resultMap;
    }
}
