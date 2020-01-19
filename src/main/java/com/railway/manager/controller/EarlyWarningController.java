package com.railway.manager.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.railway.manager.model.EarlyWarring;
import com.railway.manager.model.MonitorData;
import com.railway.manager.service.EarlyWarringService;
import com.railway.manager.vo.EarlyWarringVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @description: yujing
 * @author: lijiwen
 * @create: 2019-12-29 22:26
 **/
@Api(tags = "获取预警信息", value = "获取预警信息")
@Controller
@RequestMapping("/api/earlyWarring")
public class EarlyWarningController {
    @Resource
    private EarlyWarringService earlyWarringService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "获取列表", notes = "获取预警列表")
    public EarlyWarringVo getListUser(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String searchParam) {
        List<EarlyWarring> earlyWarrings = earlyWarringService.listAllData(pageNum, pageSize, searchParam);
        EarlyWarringVo earlyWarringVo = new EarlyWarringVo();
        earlyWarringVo.setCount(earlyWarringService.getCount(searchParam));
        earlyWarringVo.setEarlyWarrings(earlyWarrings);
        return earlyWarringVo;
    }

    @GetMapping("/haveNewWarring")
    @ResponseBody
    @ApiOperation(value = "最新报警", notes = "最新报警")
    public List<EarlyWarring> haveNewWarring(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        List<EarlyWarring> aNew = earlyWarringService.getNew();
        if (aNew.size() > 2) {
            return aNew.subList(0, 2);
        } else {
            return aNew;
        }
    }

    @GetMapping("/notice")
    @ResponseBody
    @ApiOperation(value = "通知信息", notes = "通知信息")
    public List<EarlyWarring> notice(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return earlyWarringService.getNew();
    }

    @GetMapping("/sure")
    @ResponseBody
    @ApiOperation(value = "确定报警", notes = "确定报警")
    public int sure(@RequestParam String id) {
        return earlyWarringService.setOld(id);
    }
}
