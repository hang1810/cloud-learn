package com.hang.springcloud.mapper;
import com.hang.springcloud.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Hang
 * @date 2020-08-03 19:28
 */
@Mapper
public interface SysPermissionMapper {
    @Select("SELECT * FROM sys_permission WHERE role_id=#{roleId}")
    List<SysPermission> listByRoleId(Integer roleId);
}