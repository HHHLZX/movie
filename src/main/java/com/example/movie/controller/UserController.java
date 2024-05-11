package com.example.movie.controller;


import com.example.movie.entity.User;
import com.example.movie.entity.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    public String index(Model model) {
        // 查询数据
        List<User> users = this.userRepository.findAll();
// 分配数据到html页面
        model.addAttribute("users", users);
        return "user/index";
    }

    @GetMapping("/user/delete")
    public String delete(
            @RequestParam int id
    ) {
        this.userRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/user/edit")
    public String edit(
            @RequestParam int id,
            Model model
    ) {
        Optional<User> m = this.userRepository.findById(id);
        User user = m.orElse(new User());
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/user/update")
    public String update(
            @RequestParam int id,
            @RequestParam String userTelephone,
            @RequestParam String userName,
            @RequestParam String userPassword,
            @RequestParam boolean userStatus

    ) {
        Optional<User> u = this.userRepository.findById(id);
        User user = u.orElse(null);
        if (user == null) {
            user = new User();
        }
        LocalDateTime userLasteditTime = getCurrentTime();
        System.out.println(userStatus);
        user.setUserTelephone(userTelephone);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserStatus(userStatus);
//        user.setUserAddtime(userAddtime);
        user.setUserLasteditTime(userLasteditTime);
        this.userRepository.save(user);
        return "redirect:/user/index";
    }
}
