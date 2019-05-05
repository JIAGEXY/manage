package com.lh.service.impl;

import com.lh.entity.SysAdmin;
import com.lh.entity.SysAdminExample;
import com.lh.mapper.SysAdminMapper;
import com.lh.service.SysAdminService;
import com.lh.utils.R;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAdminServiceImpl implements SysAdminService {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Override
    public R addAdmin(SysAdmin admin) {
        Md5Hash md5Hash = new Md5Hash(admin.getPassword(),admin.getUsername(),1024);
        admin.setPassword(md5Hash.toString());
        int i = sysAdminMapper.insert(admin);
        if(i>0)
            return R.ok();
        return R.error();
    }

    @Override
    public SysAdmin findByUname(String uname) {
        SysAdminExample e=new SysAdminExample();
        SysAdminExample.Criteria c=e.createCriteria();
        c.andUsernameEqualTo(uname);
        List<SysAdmin> list = sysAdminMapper.selectByExample(e);
        if(list.isEmpty()||list==null)
            return null;
        return list.get(0);
    }
}
