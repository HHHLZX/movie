package com.example.movie.controller;


import com.example.movie.entity.User;
import com.example.movie.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/index")
    public String index(Model model)
    {
        // 查询数据
        List<User> all = this.userRepository.findAll();
        // 分配数据到页面
        model.addAttribute("users", all);
        return "/user/index";
    }

    @GetMapping("/user/delete")
    public String delete(
            @RequestParam int id
    )
    {
        this.userRepository.deleteById(id);

        return "redirect:/user/index";
    }

}
