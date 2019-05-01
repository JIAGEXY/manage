package com.lh.controller;

import com.lh.mapper.MsReportMapper;
import com.lh.mapper.MsUserMapper;
import com.lh.utils.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MsReportServlet {

    @Resource
    private MsReportMapper msReportMapper;

    @Resource
    private MsUserMapper msUserMapper;

    @RequestMapping("ms/bg/report/list")
    public R getList(int pageIndex,int pageSum){
        return null;
    }


    @RequestMapping("ms/bg/do/{repordId}")
    public R yes(@PathVariable int reportId){

        return null;

    }

    @RequestMapping("ms/bg/undo/{repordId}")
    public R undo(@PathVariable int reportId){

        return null;

    }
}
