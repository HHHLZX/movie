package com.example.movie.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ExampleApiController {
    @GetMapping("/abc")
    public HashMap<String, String> hi() {
        HashMap<String, String> userInfo = new HashMap<String, String>();

        userInfo.put("userName", "ZhangSan");
        userInfo.put("userAge", "18");
        userInfo.put("useSex", "man");

        return userInfo;
    }
}
