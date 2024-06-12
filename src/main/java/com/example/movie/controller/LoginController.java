package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/api/login")
    public HashMap<String, Object> login(
            @RequestParam String userPhone,
            @RequestParam String userPassword,
            HttpSession session
    ) {

        HashMap<String, Object> res = new HashMap<>();
        if (userPhone.isEmpty() || userPassword.isEmpty()) {
            res.put("code", 2001);
            res.put("message", "手机号或者密码不能为空");
            res.put("data", null);
            return res;
        }


        HashMap<String, Object> res1 = new HashMap<String, Object>();

        // 查询用户
        User byUserPhone = this.userRepository.findByUserPhone(userPhone);
        if (byUserPhone == null) {
            res1.put("code", 2001);
            res1.put("message", "手机号码尚未注册");
            res1.put("data", userPhone);
            return res1;
        }
        // 检查密码
        boolean isPasswordOK = BCrypt.checkpw(userPassword, byUserPhone.getUserPassword());
        if (!isPasswordOK) {
            res1.put("code", 2002);
            res1.put("message", "密码错误");
            res1.put("data", userPhone);
            return res1;
        }

        // 手机号 密码 正确
        session.setAttribute("userPhone", byUserPhone.getUserPhone());
        session.setAttribute("userId", byUserPhone.getId());
        String id = session.getId();
        res1.put("code", 200);
        res1.put("message", "登录成功");
        res1.put("data", null);
        return res1;
    }
}



