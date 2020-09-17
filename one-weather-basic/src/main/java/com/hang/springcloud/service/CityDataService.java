package com.hang.springcloud.service;

import com.hang.springcloud.entiey.City;

import java.util.List;

/**
 * @author Hang
 * @date 2020-07-23 14:50
 */
public interface CityDataService {
    /**
     * 获取city 列表
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
