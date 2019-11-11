package com.railwaymanager.railwaymanager.controller;

import com.subway.manager.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: subway_manager
 * @description: 用户登陆验证
 * @author: lijiwen
 * @create: 2019-09-15 15:48
 **/
@Controller
@RequestMapping("/api")
public class LoginController {

    @GetMapping("login")
    public User login(@RequestParam String userName, @RequestParam String password) {
        return new User();
    }
}
