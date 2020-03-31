package com.tang4j.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {


//    @GetMapping("/login")
//    @ResponseBody
//    public String login() {
//        return "尚未登录，请登录";
//    }

    @PostMapping("/main")
    @ResponseBody
    public String main() {
        return "main";
    }

    @PostMapping("/userInfo")
    public String userInfo() {
        return "main";
    }


}
