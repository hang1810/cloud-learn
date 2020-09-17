package com.hang.springcloud.service;

import com.hang.springcloud.entity.SysUserRole;
import com.hang.springcloud.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hang
 * @date 2020-08-01 17:20
 */
@Service
public class SysUserRoleService {
    @Resource
//    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}