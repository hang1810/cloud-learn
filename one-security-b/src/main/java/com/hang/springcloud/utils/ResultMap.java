package com.hang.springcloud.utils;

import lombok.Data;

/**
 * @author Hang
 * @date 2020-08-05 20:34
 */
@Data
public class ResultMap {
    private String source;

    private String msg;

    public ResultMap(String source, String msg) {
        this.source = source;
        this.msg = msg;
    }
}
