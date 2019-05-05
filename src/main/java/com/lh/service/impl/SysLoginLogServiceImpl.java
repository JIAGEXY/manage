package com.lh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.entity.SysLoginlog;
import com.lh.entity.SysLoginlogExample;
import com.lh.mapper.SysLoginlogMapper;
import com.lh.service.SysLoginLogService;
import com.lh.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 *
 * @Description:
 * @Date: 2019/5/3
 * @Time: 22:11
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {
    @Resource
    private SysLoginlogMapper sysLoginlogMapper;

    @Override
    public R selectById(int userid,int pageIndex,int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        SysLoginlogExample e=new SysLoginlogExample();
        SysLoginlogExample.Criteria c=e.createCriteria();
        c.andAdminIdEqualTo(userid);
        List<SysLoginlog> list = sysLoginlogMapper.selectByExample(e);
        if(list.isEmpty()||list==null){
            return R.error();
        }
        PageInfo info=new PageInfo(list);
        return R.ok().put("data",list);
    }

    @Override
    public int addLog(SysLoginlog log) {
        return sysLoginlogMapper.insert(log);
    }
}
