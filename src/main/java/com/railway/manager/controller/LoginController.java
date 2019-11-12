package com.railway.manager.controller;

import com.railway.manager.model.User;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: railway_manager
 * @description: 用户登陆验证
 * @author: lijiwen
 * @create: 2019-09-15 15:48
 **/
@Api(tags = "用户登陆相关接口",value = "用户登陆相关接口")
@Controller
@RequestMapping("/api")
public class LoginController {

    @GetMapping("login")
    public User login(@RequestParam String userName, @RequestParam String password) {
        return new User();
    }
}
