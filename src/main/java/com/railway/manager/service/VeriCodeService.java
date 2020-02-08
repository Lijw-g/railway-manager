package com.railway.manager.service;

import com.railway.manager.common.BusinessException;
import com.railway.manager.service.redis.StringRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;


/**
 * 处理对应的验证码是否发过短信，是否登录过
 *
 * @author lijiwen
 * @version 1.0
 * @date 2019年1月11日
 */
@Service
public class VeriCodeService {
    @Autowired
    private StringRedisService stringRedisService;
    private static final String SEND_PREFIX = "send_vc_";

    /**
     * 初始化图片验证码状态
     *
     * @param code
     */
    public void initCode(String code, String phoneNumber) {
        stringRedisService.setString(getSendKey(code, phoneNumber), code, 1L, TimeUnit.MINUTES);
    }

    /**
     * 验证验证码
     *
     * @param imageCode
     * @param phoneNumber
     * @return java.lang.String
     * @Date 15:43 2019-05-09
     */
    public boolean verifyCode(String imageCode, String phoneNumber) {
        String key = getSendKey(imageCode, phoneNumber);
        String val = stringRedisService.getVal(key);
        if (ObjectUtils.isEmpty(val)) {
            return false;
        }
        if (imageCode.equals(val)) {
            //stringRedisService.delCach(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 清除缓存验证码
     *
     * @param imageCode
     * @param phoneNumber
     * @return java.lang.String
     * @Date 00:33 2019-12-03
     */
    public boolean delCacheCode(String imageCode, String phoneNumber) {
        String key = getSendKey(imageCode, phoneNumber);
        return stringRedisService.delCach(key);
    }

    private String getSendKey(String code, String phoneNumber) {

        return SEND_PREFIX + code + "_" + phoneNumber;
    }

}