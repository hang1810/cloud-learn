package com.hang.springcloud.service;
import com.hang.springcloud.entity.SysPermission;
import com.hang.springcloud.mapper.SysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hang
 * @date 2020-08-03 19:29
 */
@Service
public class SysPermissionService {


//    @Autowired
    @Resource
    private SysPermissionMapper permissionMapper;

    /**
     * 获取指定角色所有权限
     */
    public List<SysPermission> listByRoleId(Integer roleId) {
        return permissionMapper.listByRoleId(roleId);
    }
}