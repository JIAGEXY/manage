package com.lh.controller;

import com.lh.entity.SysAdmin;
import com.lh.entity.SysLoginlog;
import com.lh.service.SysAdminService;
import com.lh.service.SysLoginLogService;
import com.lh.utils.R;
import com.lh.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;

@CrossOrigin("*")
@RestController
public class AdminServlet {
    @Resource
    private SysAdminService sysAdminService;




    @RequestMapping("/ms/bg/login")
    public R login(@RequestBody SysAdmin admin){
        String s = null;
        try{
            //1，得到subject
            Subject subject = SecurityUtils.getSubject();
            String pwd = admin.getPassword();
            Md5Hash md5Hash = new Md5Hash(pwd,admin.getUsername(),1024);
            pwd = md5Hash.toString();
            //2,把用户名和密码封装成UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(),pwd);


            //3登录
            subject.login(token);//自定义Realm的认证方法

         //   System.out.println(subject.hasRole("管理员"));
         //   System.out.println(subject.isPermitted("sys:menu:save"));
            admin.setPassword(pwd);

            return R.ok().put("data",admin);
        }catch(Exception e){
            e.printStackTrace();
            s = e.getMessage();
        }

        return R.error(s);
    }

    @RequestMapping("ms/bg/admin/add")
    public R addAdmin(@RequestBody SysAdmin admin){
        return sysAdminService.addAdmin(admin);
    }

    @RequestMapping("/ms/bg/admin/logout")
    public R logout(){
        ShiroUtils.logout();
        return R.ok();
    }



}
