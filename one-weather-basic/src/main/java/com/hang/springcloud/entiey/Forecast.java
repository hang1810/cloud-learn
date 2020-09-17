package com.hang.springcloud.entiey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 未来天气
 * @author Hang
 * @date 2020-07-19 14:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Forecast implements Serializable {

    private static final long serialVersionUID = 1;
    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;
}
