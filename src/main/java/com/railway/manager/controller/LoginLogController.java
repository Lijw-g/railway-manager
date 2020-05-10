package com.railway.manager.controller;

import com.railway.manager.model.log.LoginLog;
import com.railway.manager.service.log.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 用户登录日志
 * @author: chenglin
 * @create: 2019-12-04 00:24
 **/
@Api(tags = "登录日志查询接口", value = "登录日志查询接口")
@Controller
@RequestMapping("/api/loginLog")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @GetMapping("/loginLogList")
    @ResponseBody
    @ApiOperation(value = "登录日志查询", notes = "登录日志查询")
    public Map<String, Object> loginLogList(@RequestParam(required = false) String userName,
                                           @RequestParam(required = false) String loginTime,
                                            @RequestParam(required = false) Integer pageNum,
                                            @RequestParam(required = false) Integer pageSize) {

        Map<String, Object> conditionMap = new HashMap<String, Object>();
        if (!StringUtils.isBlank(userName)) {
            conditionMap.put("userNameLike", "%" + userName.trim() + "%");
        }
        if (!StringUtils.isBlank(loginTime)) {
            conditionMap.put("loginTimeEqual", loginTime.trim());
        }

        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        if (pageNum != null && pageSize != null) {
            conditionMap.put("_limit", pageSize);
            conditionMap.put("_offset", (pageNum.intValue() - 1) * pageSize.intValue());
        }

        List<LoginLog> loginLogList = loginLogService.getList(conditionMap);
        int allCount = loginLogService.selectCount(conditionMap);

        //返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", allCount);
        resultMap.put("currentCount", loginLogList.size());
        resultMap.put("loginLogList", loginLogList);

        return resultMap;
    }
}
