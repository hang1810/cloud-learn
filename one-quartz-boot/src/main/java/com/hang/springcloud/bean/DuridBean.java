package com.hang.springcloud.bean;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @author Hang
 * @date 2020-07-26 17:08
 */
@Component
@ConfigurationProperties(prefix = "spring.dataSource.primaryDataSource")
public class DuridBean {
    private String type;

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;

    private Integer maxWait;

    private Integer timeBetweenEvictionRunsMillis;

    private Integer minEvictableIdleTimeMillis;

    private String validationQuery;

    private Boolean testWhileIdle;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;
}