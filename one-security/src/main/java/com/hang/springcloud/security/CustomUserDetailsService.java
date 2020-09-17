package com.hang.springcloud.security;

import com.hang.springcloud.entity.SysRole;
import com.hang.springcloud.entity.SysUser;
import com.hang.springcloud.entity.SysUserRole;
import com.hang.springcloud.service.SysRoleService;
import com.hang.springcloud.service.SysUserRoleService;
import com.hang.springcloud.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 默认 UserDetailService，通过用户名读取信息
 * @author Hang
 * @date 2020-08-01 17:24
 */
@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("验证账户"+username+"信息，并查找权限");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectByName(username);

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }


        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());

        for (SysUserRole userRole : userRoles) {
            log.info("userRole.getRoleId()="+userRole.getRoleId());
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(), user.getPassword(), authorities);
    }
}