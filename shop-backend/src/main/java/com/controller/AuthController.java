//图形验证码生成及验证
package com.controller;

import com.service.CaptchaService;
import com.model.entity.LoginForm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private CaptchaService captchaService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @GetMapping("/captcha")
    public ResponseEntity<String> getCaptcha(HttpSession session) throws IOException {
        String sessionId = session.getId();
        String captchaText = captchaService.generateCaptcha(sessionId);
        BufferedImage captchaImage = captchaService.getCaptchaImage(captchaText);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(captchaImage, "png", os);
        String captchaBase64 = Base64.getEncoder().encodeToString(os.toByteArray());
        return ResponseEntity.ok().body(captchaBase64);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm, HttpSession session) {
        String sessionId = session.getId();
        if (!captchaService.validateCaptcha(sessionId, loginForm.getCaptcha())) {
            return ResponseEntity.badRequest().body("验证码错误");
        }

        // 模拟验证用户名和密码
        if (!"admin".equals(loginForm.getUsername()) || !"admin".equals(loginForm.getPassword())) {
            return ResponseEntity.badRequest().body("用户名或密码错误");
        }

        // 登录成功，生成JWT Token
        String token = Jwts.builder()
                .setSubject(loginForm.getUsername())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        return ResponseEntity.ok().body(token);
    }
}
