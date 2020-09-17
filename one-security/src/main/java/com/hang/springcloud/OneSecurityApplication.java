package com.hang.springcloud;

import com.hang.springcloud.servlet.VerifyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Hang
 * @date 2020-08-01 17:05
 */
@SpringBootApplication
public class OneSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(OneSecurityApplication.class,args);
    }

    /**
     * 注入验证码servlet
     */
    @Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyServlet());
        registration.addUrlMappings("/getVerifyCode");
        return registration;
    }
}
