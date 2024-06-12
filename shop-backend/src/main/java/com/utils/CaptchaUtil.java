//图形验证码生成及验证服务类
package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateCaptcha() {
        String captchaKey = UUID.randomUUID().toString();
        String captchaValue = UUID.randomUUID().toString().substring(0, 6); // 简单生成6位验证码
        redisTemplate.opsForValue().set(captchaKey, captchaValue, 5, TimeUnit.MINUTES);
        return captchaKey + ":" + captchaValue; // 返回验证码键值对
    }

    public boolean validateCaptcha(String captchaKey, String captchaValue) {
        String storedCaptcha = redisTemplate.opsForValue().get(captchaKey);
        return storedCaptcha != null && storedCaptcha.equalsIgnoreCase(captchaValue);
    }
}

