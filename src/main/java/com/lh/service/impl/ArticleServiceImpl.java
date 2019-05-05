package com.lh.service.impl;


import com.github.pagehelper.PageInfo;
import com.lh.entity.MsArticle;
import com.lh.entity.MsArticleExample;
import com.lh.entity.MsReport;
import com.lh.entity.MsReportExample;
import com.lh.mapper.MsArticleMapper;
import com.lh.mapper.MsReportMapper;
import com.lh.service.ArticleService;
import com.lh.utils.R;
import com.lh.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private MsArticleMapper msArticleMapper;
    @Resource
    private MsReportMapper msReportMapper;

    //文章模糊查询
    @Override
    public R articleLike(String str) {
        MsArticleExample example=new MsArticleExample();
        MsArticleExample.Criteria criteria = example.createCriteria();
        List<MsArticle> msArticles = msArticleMapper.selectLike(str);
        PageInfo<MsArticle> info=new PageInfo<>(msArticles);
        return R.ok().put("data",info);
    }

    //文章全查
    @Override
    public R allArticle(){
        List<MsArticle> msArticles = msArticleMapper.selectAll();
        PageInfo<MsArticle> info=new PageInfo<>(msArticles);
        return R.ok().put("data",info);
    }

    //按类型查举报文章
    @Override
    public R reportType(String str) {
        //举报信息
        MsReportExample example=new MsReportExample();
        MsReportExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo("文章");
        criteria.andReasonEqualTo(str);
        List<MsReport> security = msReportMapper.selectByExample(example);
        PageInfo<MsReport> info = new PageInfo<>(security);
        //文章信息
        MsArticleExample example1=new MsArticleExample();
        MsArticleExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andStatusEqualTo(-1);
        List<MsArticle> msArticles = msArticleMapper.selectByExample(example1);
        PageInfo<MsArticle> info1=new PageInfo<>(msArticles);

        Map<String,Object> map=new HashMap<>();
        map.put("repo",info);
        map.put("arti",info1);
        return R.ok().put("data",map);
    }

    //举报文章全查
    @Override
    public R allReport(){
        MsReportExample example=new MsReportExample();
        MsReportExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo("文章");
        List<MsReport> msReports = msReportMapper.selectByExample(example);
        PageInfo<MsReport> info=new PageInfo<>(msReports);
        return R.ok().put("data",info);

    }

    //举报合理按钮
    @Override
    public R status(MsArticle msArticle){
        msArticle.setStatus(-1);
        msArticleMapper.updateByPrimaryKeySelective(msArticle);
        return R.ok("举报合理");
    }
}
