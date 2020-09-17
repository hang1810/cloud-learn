package com.hang.springcloud.job;

import com.hang.springcloud.entiey.City;
import com.hang.springcloud.entiey.Weather;
import com.hang.springcloud.service.CityDataService;
import com.hang.springcloud.service.WeatherDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * Weather Data Sync Job
 * @author Hang
 * @date 2020-07-20 20:16
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Weather Data Sync Job. Start！");

        List<City> cityList = null;
        try{
            //获取城市列表
            cityList = cityDataService.listCity();
        } catch (Exception e){
            logger.error("Exception! "+e);
        }

        //便利城市id 获取天气
        for (City city : cityList){
            String  cityId = city.getCityId();
            logger.info("weather Data Sync Job .city:"+cityId);
            weatherDataService.syncDataByCityId(cityId);
        }
        logger.info("Weather Data Sync Job. END！");
    }
}
