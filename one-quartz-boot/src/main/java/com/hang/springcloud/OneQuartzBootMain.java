package com.hang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Hang
 * @date 2020-07-26 17:06
 */
@SpringBootApplication
@EnableScheduling
public class OneQuartzBootMain {
    public static void main(String[] args) {
        SpringApplication.run(OneQuartzBootMain.class,args);
    }
}
