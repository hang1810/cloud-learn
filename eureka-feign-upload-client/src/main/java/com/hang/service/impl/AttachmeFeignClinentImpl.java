package com.hang.service.impl;

import com.hang.service.AttachmeFeignClinent;
import feign.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Hang
 * @date 2020-10-09 21:50
 */
@Component
public class AttachmeFeignClinentImpl implements AttachmeFeignClinent {

    @Override
    public String upload(MultipartFile file, String projectId, String user) throws Throwable {
        return "请求超时";
    }

    @Override
    public Response downloadAttachme(String attachemId) throws Throwable {
        return null;
    }
}