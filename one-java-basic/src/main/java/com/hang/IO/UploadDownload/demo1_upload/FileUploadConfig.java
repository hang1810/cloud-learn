package com.hang.IO.UploadDownload.demo1_upload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author Hang
 * @date 2020-10-09 19:38
 */
@Configuration
public class FileUploadConfig {

//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//
//        //factory.setMaxFileSize(1024);
//        //单个文件最大
//        factory.setMaxFileSize(DataSize.ofBytes(10*10*1024));
//        /// 设置总上传数据总大小
//        factory.setMaxRequestSize(DataSize.ofBytes(10*10*1024));
//        return factory.createMultipartConfig();
//    }
//

}
