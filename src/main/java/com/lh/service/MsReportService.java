package com.lh.service;

import com.lh.utils.R;

public interface MsReportService {


    public R getList(int pageIndex,int pageSize,String reason);

    public R getInfo(long reportId);

    public R yes(long comId);

    public R undo(long comId);
}
