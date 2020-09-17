package com.hang.springcloud.entiey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 昨日天气
 * @author Hang
 * @date 2020-07-19 14:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Yeaterday implements Serializable {
    private static final long serialVersionUID = 1L;
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
}
