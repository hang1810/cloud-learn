package com.hang;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hang
 * @date 2020-10-09 20:35
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaFeignServerMain {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignServerMain.class,args);
    }
    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

}
