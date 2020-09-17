package com.hang.springcloud.security;

import com.hang.springcloud.entity.SysPermission;
import com.hang.springcloud.service.SysPermissionService;
import com.hang.springcloud.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
/**
 * Spring Security 权限管理   默认权限处理
 * @author Hang
 * @date 2020-08-03 19:35
 */
@Slf4j
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private SysRoleService roleService;

    /**
     *
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        log.info("查看是否含有该权限");
        // 获得loadUserByUsername()方法的结果 此处该方法已经重写  从CustomAuthenticationProvider  authentication方法获取
        User user = (User) authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Integer roleId = roleService.selectByName(roleName).getId();
            // 得到角色所有的权限
            List<SysPermission> permissionList = permissionService.listByRoleId(roleId);

            // 遍历权限
            for (SysPermission sysPermission : permissionList) {
                // 获取权限集
                List permissions = sysPermission.getPermissions();
                log.info(sysPermission.getPermissions().toString());
                // 如果访问的Url和权限用户符合的话，返回true
                log.info("targetUrl="+targetUrl+"||||sysPermission.getUrl()="+sysPermission.getUrl());
                log.info("permissions="+permissions+"||||targetPermission ="+targetPermission);
                if (targetUrl.equals(sysPermission.getUrl()) && permissions.contains(targetPermission)) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
