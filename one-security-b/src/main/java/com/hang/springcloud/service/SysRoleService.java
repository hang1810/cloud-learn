package com.hang.springcloud.service;

import com.hang.springcloud.entity.SysRole;
import com.hang.springcloud.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Hang
 * @date 2020-08-01 17:19
 */
@Service
public class SysRoleService {

//    @Autowired
    @Resource
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }
}
