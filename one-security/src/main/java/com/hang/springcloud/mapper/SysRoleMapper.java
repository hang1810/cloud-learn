package com.hang.springcloud.mapper;

import com.hang.springcloud.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Hang
 * @date 2020-08-01 17:06
 */
@Mapper
public interface SysRoleMapper {

    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);

    @Select("SELECT * FROM sys_role WHERE name = #{name}")
    SysRole selectByName(String name);
}
