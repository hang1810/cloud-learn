package com.hang.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hang
 * @date 2020-08-01 17:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;
}
