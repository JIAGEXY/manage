package com.lh.controller;

import com.lh.mapper.MsReportMapper;
import com.lh.mapper.MsUserMapper;
import com.lh.service.MsReportService;
import com.lh.utils.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MsReportServlet {

    @Resource
    private MsReportService msReportService;

    @RequestMapping("/ms/bg/report/list")
    public R getList(int pageIndex,int pageSize,String reason){

        return msReportService.getList(pageIndex,pageSize,reason);
    }

    @RequestMapping("/ms/bg/report/info/{repordId}")
    public R getInfo(@PathVariable long reportId){

        return msReportService.getInfo(reportId);

    }


    @RequestMapping("/ms/bg/do/{comId}")
    public R yes(@PathVariable long comId){

        return msReportService.yes(comId);

    }

    @RequestMapping("/ms/bg/undo/{comId}")
    public R undo(@PathVariable long comId){

        return null;

    }
}
