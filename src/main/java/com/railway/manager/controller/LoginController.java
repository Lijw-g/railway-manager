package com.railway.manager.controller;

import com.railway.manager.model.User;
import com.railway.manager.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 用户注册及登录验证
 * @author: lijiwen
 * @create: 2019-09-15 15:48
 **/
@Api(tags = "用户注册及登录相关接口",value = "用户注册及登录相关接口")
@Controller
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public User login(@RequestParam String userName, @RequestParam String password) {

        System.out.println("账号：" + userName + "==密码：" + password);

        return new User();
    }

    @PostMapping("/registerUser")
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Map<String,Object> registerUser(@RequestParam String userName, @RequestParam String password, @RequestParam String pwdConfirm) {

        Map<String,Object> resultMap = new HashMap<String,Object>();

        if(StringUtils.isBlank(userName)) {

            resultMap.put("code","501");
            resultMap.put("description","用户名不能为空");
            return resultMap;
        }

        //检查用户名是否唯一
        if(!userService.checkUserName(userName)) {
            resultMap.put("code","502");
            resultMap.put("description","用户名已被注册");
            return resultMap;
        }

        //检查两次输入密码是否一致
        if(StringUtils.isBlank(password)) {
            resultMap.put("code","503");
            resultMap.put("description","密码不能为空");
            return resultMap;
        }
        if(!password.trim().equals(pwdConfirm)) {
            resultMap.put("code","504");
            resultMap.put("description","确认密码与密码不一致");
            return resultMap;
        }

        //添加用户
        User user = new User();
        user.setUserName(userName.trim());
        user.setDisplayName(user.getUserName());
        user.setPassword(password.trim());
        user.setCreateUser(user.getUserName());
        user.setUpdateUser(user.getUserName());

        try {
            int num = userService.add(user);

            if(num > 0) {
                resultMap.put("code","200");
                resultMap.put("description","注册成功");
                resultMap.put("user",user);
                return resultMap;
            } else {
                resultMap.put("code","505");
                resultMap.put("description","注册失败");
                return resultMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code","506");
            resultMap.put("description","注册失败");
            return resultMap;
        }
    }
}
