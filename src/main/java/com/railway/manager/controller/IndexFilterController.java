package com.railway.manager.controller;

import com.railway.manager.model.Filters;
import com.railway.manager.service.IndexFilterService;
import com.railway.manager.vo.CoreDataVo;
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

/**
 * @program: railway-manager
 * @description:
 * @author: lijiwen
 * @create: 2019-12-28 16:38
 **/
@Api(tags = "过滤条件", value = "过滤条件")
@Controller
@RequestMapping("/api/filter")
public class IndexFilterController {
    @Resource
    private IndexFilterService indexFilterService;

    @GetMapping("/listFactory")
    @ResponseBody
    @ApiOperation(value = "厂家信息", notes = "厂家信息")
    public List<Filters> listFactory() {
        return indexFilterService.listFactory();
    }

    @GetMapping("/listLine")
    @ResponseBody
    @ApiOperation(value = "线路信息", notes = "线路信息")
    public List<Filters> listLine() {
        return indexFilterService.listLine();
    }

    @GetMapping("/listCity")
    @ResponseBody
    @ApiOperation(value = "城市信息", notes = "城市信息")
    public List<Filters> listCity() {
        return indexFilterService.listCity();
    }

    @GetMapping("/listSituation")
    @ResponseBody
    @ApiOperation(value = "状态信息", notes = "状态信息")
    public List<Filters> listSituation() {
        return indexFilterService.listSituation();
    }
}
