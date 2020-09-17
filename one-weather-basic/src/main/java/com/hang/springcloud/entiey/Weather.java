package com.hang.springcloud.entiey;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 天气信息
 * @author Hang
 * @date 2020-07-19 14:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Weather implements Serializable {
     private static final long serialVersionUID = 1L;

    private String city;
    private String aqi;
    private String ganmao;
    private String wendu;
    private Yeaterday yesterday;
    private List<Forecast> forecast;
}
