package com.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaService {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateCaptcha(String sessionId) {
        String captchaText = captchaProducer.createText();
        String captchaKey = "captcha:" + sessionId;
        redisTemplate.opsForValue().set(captchaKey, captchaText, 5, TimeUnit.MINUTES);
        return captchaText;
    }

    public BufferedImage getCaptchaImage(String captchaText) {
        return captchaProducer.createImage(captchaText);
    }

    public boolean validateCaptcha(String sessionId, String captcha) {
        String captchaKey = "captcha:" + sessionId;
        String storedCaptcha = redisTemplate.opsForValue().get(captchaKey);
        return storedCaptcha != null && storedCaptcha.equalsIgnoreCase(captcha);
    }
}
