package com.railway.manager.controller;

import com.railway.manager.common.BusinessException;
import com.railway.manager.service.VeriCodeService;
import com.railway.manager.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName VerifyCodeController
 * @Description
 * @Author lee
 * @Datev$ 2019/2/22
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
    public String createImage(HttpServletResponse response, @RequestParam String phoneNumber) {
        // 生成验证图片返回给前台：4位纯数字验证码
        String verifyCode = VerifyCodeUtils.get4Code();
        veriCodeService.initCode(verifyCode, phoneNumber);
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

    @ApiOperation(value = "验证验证码")
    @GetMapping("/verify")
    @ResponseBody
    public String verifyCode(@RequestParam String imageCode, @RequestParam String userTaskId) throws BusinessException {
        return veriCodeService.verifyCode(imageCode, userTaskId);
    }
}
