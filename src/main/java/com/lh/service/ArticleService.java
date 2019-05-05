package com.lh.service;

import com.lh.entity.MsArticle;
import com.lh.utils.R;

public interface ArticleService {
    R articleLike(String str);
    R allArticle();
    R reportType(String str);
    R allReport();
    R status(MsArticle msArticle);
}
