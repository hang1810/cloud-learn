package com.hang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Hang
 * @date 2020-07-19 13:59
 */

@EnableScheduling  // 开启spring自带定时任务
@SpringBootApplication
public class OneQuartzMain {
    public static void main(String[] args) {
        SpringApplication.run(OneQuartzMain.class,args);
    }
}
