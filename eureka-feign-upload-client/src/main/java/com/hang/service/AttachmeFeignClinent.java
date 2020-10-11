package com.hang.service;

import com.hang.service.impl.AttachmeFeignClinentImpl;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Hang
 * @date 2020-10-09 21:49
 */

@FeignClient(name = "bidding",fallback = AttachmeFeignClinentImpl.class)
public interface AttachmeFeignClinent {

    /*
     * 附件上传保存
     * */
    @PostMapping(value = "/attachmeController/multifileUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart("file") MultipartFile file,
                  @RequestParam("projectId") String projectId,
                  @RequestParam("user" ) String user)throws Throwable;

    /*
     * 附件下载
     * 通过Response来接收文件流
     * */
    @PostMapping("/attachmeController/downloadAttachem")
    Response downloadAttachme(@RequestBody String attachemId) throws Throwable;
}


