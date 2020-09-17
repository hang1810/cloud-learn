package com.hang.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 权限实体类
 * @author Hang
 * @date 2020-08-03 19:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String url;

    private Integer roleId;

    private String permission;

    private List permissions;

    public List getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }
}
