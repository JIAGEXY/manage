package com.lh.controller;

import com.lh.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminServlet {
    @RequestMapping("/login")
    public R login(){
        return R.error("欢迎使用");
    }
}
