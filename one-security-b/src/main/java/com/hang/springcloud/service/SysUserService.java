package com.hang.springcloud.service;

import com.hang.springcloud.entity.SysUser;
import com.hang.springcloud.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Hang
 * @date 2020-08-01 17:15
 */
@Service
public class SysUserService {



//    @Autowired
    @Resource
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
