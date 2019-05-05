package com.lh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.entity.MsReport;
import com.lh.entity.MsReportExample;
import com.lh.entity.MsUser;
import com.lh.mapper.MsReportMapper;
import com.lh.mapper.MsUserMapper;
import com.lh.service.MsReportService;
import com.lh.utils.R;
import com.lh.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MsReportServiceImpl implements MsReportService {

    @Resource
    private MsReportMapper msReportMapper;
    @Resource
    private MsUserMapper msUserMapper;

    @Override
    public R getList(int pageIndex, int pageSize, String reason) {


        PageHelper.startPage(pageIndex,pageSize);
        MsReportExample example = new MsReportExample();
        MsReportExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotEmpty(reason)){
            criteria.andReasonLike(reason);
        }
        criteria.andTypeEqualTo("房东");
        List<MsReport> msReports = msReportMapper.selectByExample(example);

        if(msReports.size() != 0){
            for (MsReport msReport : msReports) {
                MsUser msUser = msUserMapper.selectByPrimaryKey(msReport.getComid());

                msReport.setUsername(msUser.getUsername());
                msReport.setPhone(msUser.getPhone());
            }
        }


        PageInfo info = new PageInfo(msReports);

        return R.ok().put("data",info);
    }

    @Override
    public R getInfo(long reportId) {

        MsReport msReport = msReportMapper.selectByPrimaryKey(reportId);

        return R.ok().put("data",msReport);
    }

    @Override
    public R yes(long comId) {

        MsUser msUser = msUserMapper.selectByPrimaryKey(comId);

        if(msUser.getIntegration()>100){
            msUser.setIntegration(msUser.getIntegration()-100);

        }else {
            msUser.setLevel(msUser.getLevel()-1);
            msUser.setIntegration(msUser.getIntegration()+900);

        }

        int i = msUserMapper.updateByPrimaryKey(msUser);

        MsReportExample example = new MsReportExample();
        MsReportExample.Criteria criteria = example.createCriteria();
        criteria.andComidEqualTo(comId);

        msReportMapper.deleteByExample(example);

        return R.ok();
    }

    @Override
    public R undo(long comId) {

        MsReportExample example = new MsReportExample();
        MsReportExample.Criteria criteria = example.createCriteria();
        criteria.andComidEqualTo(comId);

        msReportMapper.deleteByExample(example);


        return R.ok();
    }
}
