package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.entity.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class UserController {
    public UserRepository userRepository;

    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/create")
    public String create() {
        return "user/create";
    }

    @PostMapping("/user/save")
//    @ResponseBody
    public String save(
            @RequestParam String userTelephone,
            @RequestParam String userName,
            @RequestParam String userPassword
    ) {
        LocalDateTime userAddtime = getCurrentTime();
        LocalDateTime userLasteditTime = getCurrentTime();
        boolean userStatus = true;
        //接收数据
        User user = new User();
        user.setUserTelephone(userTelephone);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserStatus(userStatus);
        user.setUserAddtime(userAddtime);
        user.setUserLasteditTime(userLasteditTime);

        //存储数据
        this.userRepository.save(user);
        return "redirect:/movie/index";
    }
}
