package com.hang.springcloud.entiey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hang
 * @date 2020-07-19 15:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Weather data;
    private Integer status;
    private String desc;
}
