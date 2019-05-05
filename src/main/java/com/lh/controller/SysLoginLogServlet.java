package com.lh.controller;

import com.lh.entity.SysAdmin;
import com.lh.service.SysLoginLogService;
import com.lh.utils.R;
import com.lh.utils.ShiroUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
 * @Time: 22:21
 */
@RestController
@CrossOrigin("*")
public class SysLoginLogServlet {

    @Resource
    private SysLoginLogService sysLoginLogService;

    @RequestMapping("ms/bg/admin/loglist")
    public R selectlist( int pageIndex, int pageSize){
        System.out.println(pageIndex+pageSize+"-----------------------------");
        SysAdmin admin = (SysAdmin)ShiroUtils.getCurrentUser();
        return sysLoginLogService.selectById(admin.getAdminId().intValue(),pageIndex,pageSize);
    }
}
