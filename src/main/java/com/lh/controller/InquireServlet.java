package com.lh.controller;

import com.lh.service.InquireService;
import com.lh.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InquireServlet {

    @Resource
    private InquireService inquireService;

    @RequestMapping("/ms/bg/order/inquire")
    @ResponseBody
    public R findList(int pageIndex, int pageSum, Integer status){
        return inquireService.find(pageIndex, pageSum, status);
    }
}
