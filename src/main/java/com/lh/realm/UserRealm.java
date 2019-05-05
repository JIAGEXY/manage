package com.lh.realm;

import com.lh.entity.SysAdmin;
import com.lh.entity.SysLoginlog;
import com.lh.exception.RZException;
import com.lh.service.SysAdminService;
import com.lh.service.SysLoginLogService;
import com.lh.service.SysMenuService;
import com.lh.service.SysRoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysAdminService sysAdminService;

    @Resource
    private SysRoleService sysRoleService;


    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysLoginLogService sysLoginLogService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("----->授权");
        //得到当前登录的用户
        SysAdmin user = (SysAdmin) principal.getPrimaryPrincipal();
        //根据当前用户id查询角色名
        List<String> roles = sysRoleService.findRolesByUserId(user.getAdminId());
        //再查询权限
        List<String> perms = sysMenuService.findPermsByUserId(user.getAdminId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRoles(roles);
        info.addStringPermissions(perms);
        System.out.println("----->授权over!");

        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        System.out.println("----->认证");
        UsernamePasswordToken u = (UsernamePasswordToken) authenticationToken;
        String uname = u.getUsername();
        String pass = new String(u.getPassword());

        //调用service层方法
        SysAdmin user = sysAdminService.findByUname(uname);

        if (user==null){
            throw new UnknownAccountException("账号未知！");
        }
        if (!user.getPassword().equals(pass)){
            throw new IncorrectCredentialsException("密码错误！");
        }
        if (user.getStatus()==0){
            throw new LockedAccountException("账号被冻结！");
        }

        SysLoginlog log=new SysLoginlog();
        log.setLoginitme(new Date(System.currentTimeMillis()));
        log.setAdminId(user.getAdminId().intValue());
        int i = sysLoginLogService.addLog(log);
        if(i<0)
            throw new RZException("网络出错，请您重试");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,pass,this.getName());

        return info;
    }
}
