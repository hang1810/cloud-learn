package com.hang.springcloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hang.springcloud.entiey.CommonResult;
import com.hang.springcloud.entiey.WeatherResponse;
import com.hang.springcloud.service.WeatherDataService;
import com.hang.springcloud.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Hang
 * @date 2020-07-19 15:23
 */
@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIEM_OUT=360L;//1800
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeahter(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeahter(uri);
    }

    @Override
    public CommonResult<WeatherResponse> getDataByCityNameAnther(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeahterAnther(uri);
    }


    /*
     * @Description:
     * @Author: Hang
     * @Date: 2020-07-19 15:51
     * @param: [uri]
     * @return: com.hang.springcloud.entiey.WeatherResponse
     **/
    private WeatherResponse doGetWeahter(String uri)   {
        String key = uri;
        String strBody = null;
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        ResponseEntity<String> respString=null;
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();//获取redis 缓存数据
        // 先查缓存，缓存有的取缓存中的数据
        try {
            if (redisTemplate.hasKey(key)){
                log.info("Redis has data");
                strBody=valueOperations.get(key);
            }else{
                log.info("Redis don't has data");
                // 缓存没有，再调用服务接口来获取 并写入redis
                respString = restTemplate.getForEntity(uri, String.class);
                if (respString.getStatusCodeValue() == 200) {
                    strBody =  StringUtils.conventFromGzip(respString.getBody()); //StringUtils.conventFromGzip()处理天气接口返回乱码
                    log.info(key);
                }
                valueOperations.set(key,strBody,TIEM_OUT, TimeUnit.SECONDS);
                log.info(strBody);
            }
            resp = mapper.readValue(strBody, WeatherResponse.class);//将返回的数据转化为WeatherResponse对象
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 把天气数据放在缓存中
     * @param cityId
     */
    @Override
    public void syncDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存中
     * @param uri
     */
    private void saveWeatherData(String uri){
        String key=uri;
        String strBody = null;
        WeatherResponse resp = null;
        ResponseEntity<String> respString=null;
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();//获取redis 缓存数据

        try {
            //调用服务接口 获取数据
//            log.info(uri);
            respString = restTemplate.getForEntity(uri, String.class);
            if (respString.getStatusCodeValue() == 200) {
                strBody =  StringUtils.conventFromGzip(respString.getBody()); //StringUtils.conventFromGzip()处理天气接口返回乱码

            }
            //将数据写入缓存
            valueOperations.set(key,strBody,TIEM_OUT, TimeUnit.SECONDS);
            log.info(strBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /*
     * @Description:
     * @Author: Hang
     * @Date: 2020-07-19 16:04
     * @param: [uri]
     * @return: com.hang.springcloud.entiey.WeatherResponse
     **/
    private CommonResult<WeatherResponse> doGetWeahterAnther(String uri) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(uri, CommonResult.class);
        String strBody = null;
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCodeValue() + entity.toString());
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "查询失败");
        }
    }
}
