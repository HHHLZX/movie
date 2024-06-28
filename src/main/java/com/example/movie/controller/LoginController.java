package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
            HttpSession session,
            HttpServletResponse response // 自动注入 HttpServletResponse
    ) {
        HashMap<String, Object> res = new HashMap<>();
        if (userPhone.isEmpty() || userPassword.isEmpty()) {
            res.put("code", 2001);
            res.put("message", "手机号或者密码不能为空");
            res.put("data", null);
            return res;
        }

        // 查询用户
        User byUserPhone = this.userRepository.findByUserPhone(userPhone);
        if (byUserPhone == null) {
            res.put("code", 2001);
            res.put("message", "手机号码尚未注册");
            res.put("data", userPhone);
            return res;
        }

        // 检查密码
        boolean isPasswordOK = BCrypt.checkpw(userPassword, byUserPhone.getUserPassword());
        if (!isPasswordOK) {
            res.put("code", 2002);
            res.put("message", "密码错误");
            res.put("data", userPhone);
            return res;
        }

        // 手机号 密码 正确
        session.setAttribute("userPhone", byUserPhone.getUserPhone());
        session.setAttribute("userId", byUserPhone.getId());
        String session_id = session.getId();
        System.out.println(session_id);
        System.out.println(session.getAttribute("userId"));
        res.put("code", 200);
        res.put("message", "登录成功");
        res.put("data", null);
        res.put("userid", byUserPhone.getId());

        // 设置 Cookie
        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);

        return res;
    }
}
