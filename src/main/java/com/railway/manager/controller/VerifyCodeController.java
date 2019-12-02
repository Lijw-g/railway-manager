package com.railway.manager.controller;

import com.railway.manager.service.VeriCodeService;
import com.railway.manager.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName VerifyCodeController
 * @Description
 * @Author chenglin
 * @Datev$ 2019/12/02
 * @version 1.2
 **/
@Api(value = "生成图片验证码和验证", description = "生成图片验证码和验证", tags = {"生成图片验证码和验证"})
@Controller
@RequestMapping("/api/verify")
public class VerifyCodeController  {
    @Resource
    private VeriCodeService veriCodeService;

    @ApiOperation(value = "获取图形验证码")
    @GetMapping("/createImage")
    @ResponseBody
    public String createImage(HttpServletResponse response, @RequestParam String userTaskId) {
        //检查唯一标识是否有值
        if(StringUtils.isBlank(userTaskId)) {
            return "error";
        }
        userTaskId = userTaskId.trim();

        // 生成验证图片返回给前台：4位纯数字验证码
        String verifyCode = VerifyCodeUtils.get4Code();
        System.out.println("验证码：" + verifyCode);
        veriCodeService.initCode(verifyCode, userTaskId);
        // 禁止图像缓存。
        response.reset();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        try {
            return "data:image/png;base64," + VerifyCodeUtils.outputImageBase64(100, 35, verifyCode);
        } catch (IOException e) {
        }
        return null;
    }

    @ApiOperation(value = "验证图片验证码")
    @PostMapping("/verifyCode")
    @ResponseBody
    public Map<String,String> verifyCode(@RequestParam String imageCode, @RequestParam String userTaskId) {

        Map<String,String> resultMap = new HashMap<String,String>();

        if(StringUtils.isBlank(imageCode)) {
            resultMap.put("code","502");
            resultMap.put("description","请输入验证码");
            return resultMap;
        }
        if(StringUtils.isBlank(userTaskId)) {
            resultMap.put("code","503");
            resultMap.put("description","唯一标识错误");
            return resultMap;
        }

        //验证通过
        if(veriCodeService.verifyCode(imageCode.trim(), userTaskId.trim())) {
            resultMap.put("code","200");
            resultMap.put("description","验证通过");
        } else {
            resultMap.put("code","501");
            resultMap.put("description","验证码错误");
        }

        return resultMap;
    }
}
