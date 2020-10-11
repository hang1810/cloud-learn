package com.hang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Hang
 * @date 2020-10-09 20:50
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignUploadClientMain {
    public static void main(String[] args) {
        SpringApplication.run(FeignUploadClientMain.class,args);
    }
}
