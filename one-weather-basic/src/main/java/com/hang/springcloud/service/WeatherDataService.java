package com.hang.springcloud.service;

import com.hang.springcloud.entiey.CommonResult;
import com.hang.springcloud.entiey.WeatherResponse;

/**
 * @author Hang
 * @date 2020-07-19 14:23
 */
public interface WeatherDataService {

        /**
         * 根据城市ID查询天气数据
         *
         * @param cityId
         * @return
         */
        WeatherResponse getDataByCityId(String cityId);

        /**
         * 根据城市名称查询天气数据
         *
         * @param cityName
         * @return
         */
        WeatherResponse getDataByCityName(String cityName);
        CommonResult<WeatherResponse> getDataByCityNameAnther(String cityName);

        void syncDataByCityId(String cityId);
    }
