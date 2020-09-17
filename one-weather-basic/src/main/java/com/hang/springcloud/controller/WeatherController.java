package com.hang.springcloud.controller;

import com.hang.springcloud.entiey.CommonResult;
import com.hang.springcloud.entiey.WeatherResponse;
import com.hang.springcloud.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hang
 * @date 2020-07-19 16:11
 */
@RestController
@Slf4j
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId) {
        return weatherDataService.getDataByCityId(cityId);
    }

    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherByCityName(@PathVariable("cityName") String cityName) {
        return weatherDataService.getDataByCityName(cityName);
    }

    @GetMapping("/cityNameAnther/{cityNameAnther}")
    public CommonResult<WeatherResponse> getWeatherByCityNameAnther(@PathVariable("cityNameAnther") String cityName) {
        return weatherDataService.getDataByCityNameAnther(cityName);
    }
}
