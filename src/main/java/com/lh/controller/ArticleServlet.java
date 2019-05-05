package com.lh.controller;

import com.lh.entity.MsArticle;
import com.lh.service.ArticleService;
import com.lh.utils.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


@RestController
public class ArticleServlet {
    @Resource
    private ArticleService articleService;
    //文章模糊查询
    @RequestMapping("/ms/bg/articleList/{str}")
    public R articleLike(@PathVariable(value="str") String str){
        return articleService.articleLike(str);
    }
    //全查
    @RequestMapping("/ms/bg/articleList")
    public R allArticle(){
        return articleService.allArticle();
    }

    //按类型查举报文章
    @RequestMapping("/ms/bg/reportList1")
    public R reportType(String str){
        return articleService.reportType(str);
    }
    //举报文章全查
    @RequestMapping("/ms/bg/reportList")
    public R allReport(){
        return articleService.allReport();
    }

    //举报合理按钮
    @RequestMapping("/ms/bg/lolo")
    public R status(MsArticle msArticle){
        return articleService.status(msArticle);
    }

}
