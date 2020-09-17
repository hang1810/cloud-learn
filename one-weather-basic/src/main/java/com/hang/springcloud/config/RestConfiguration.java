package com.hang.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Hang
 * @date 2020-07-19 14:05
 */
@Configuration
public class RestConfiguration {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

//    @Bean
//    public RestTemplate restTemplate() {
//        return restTemplateBuilder.build();
//    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//        //消息转换器列表
//        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
//        //配置消息转换器StringHttpMessageConverter，并设置utf-8
//        messageConverters.set(1,  new StringHttpMessageConverter(StandardCharsets.UTF_8));//支持中文字符集，默认ISO-8859-1，支持utf-8
//        return restTemplate;
//    }
 }
