package com.lh.service;

import com.lh.entity.SysAdmin;
import com.lh.utils.R;

public interface SysAdminService {
    R addAdmin(SysAdmin admin);

    SysAdmin findByUname(String uname);
}
