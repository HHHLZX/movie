package com.example.movie.api;

import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.regex.Pattern;


@RestController
public class RegisterController {
    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    注册
    @PostMapping("/api/reg")
    public HashMap<String, Object> reg(
            @RequestParam String userPhone,
            @RequestParam String userPassword
    )
    {
        HashMap<String, Object> res = new HashMap<>();

        //校验手机号是否合法， 规则: 以 1 开头， 11 位的数字
        if (userPhone.isEmpty() || !Pattern.matches("^1[0-9]{10}$", userPhone)){
            res.put("code", 1001);
            res.put("message", "手机号码格式错误");
            res.put("data", userPhone);
            return res;
        }
        // 检查手机号是否被使用
        User byUserPhone = this.userRepository.findByUserPhone(userPhone);
        if (byUserPhone != null){
            res.put("code", 1002);
            res.put("message", "手机号已经被使用");
            res.put("data", userPhone);
            return res;
        }
        // 判断密码是否为空
        if (userPassword.isEmpty()){
            res.put("code", 1003);
            res.put("message", "密码不能为空");
            res.put("data", null);
            return res;
        }

        User user = new User();
        user.setUserPhone(userPhone);
        /**
         * 参数1： 待加密的字符串
         * 参数2： salt 加密的盐
         */
        userPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());
        user.setUserPassword(userPassword);
        user.setCreated_at(LocalDateTime.now());
        this.userRepository.save(user);

        res.put("code", 0);
        res.put("message", "注册成功");
        res.put("data", null);

        return res;
    }
}
