//package com.hang.controller;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.biddingportal.model.annextem.query.AnnexItemQuery;
//import com.biddingportal.model.attachme.query.AttachmeQuery;
//import com.hang.model.commons.Result;
//import com.hang.service.AttachmeFeignClinent;
//import feign.Response;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;
//import java.io.InputStream;
//
//
///**
// * @author Hang
// * @date 2020-10-09 21:48
// */
//@RestController
//@CrossOrigin(origins = "*",maxAge = 3600) //解决跨域
//@RequestMapping("/attachmeController")
//public class AttachmeController {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private AttachmeFeignClinent attachmeFeignClinent;
//
//    /*附件文件上传
//     *
//     * */
//    @PostMapping(value = "/multifileUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Result upload(@RequestParam("file") MultipartFile file ,
//                         @RequestParam("projectId") String projectId,
//                         @RequestParam("user" ) String user) throws Throwable {
//
//        //通过远程访问，访问bidding系统
//        String result = attachmeFeignClinent.upload(file,projectId,user);
//        //把json字符串转化成javabean对象
//        Result resultObj = JSONObject.parseObject(result,Result.class);
//        //输入日志
//        logger.info("上传附件成功");
//        //返回结果
//        return resultObj;
//
//    }
//
//
//    /*
//       * 附件文件下载，这里需要返回ResponseEntity<byte[]>对象，而feignClient是通过Response
//         来接收服务提供者方返回的文件
//       * */
//    @PostMapping("/downloadAttachem")
//    public ResponseEntity<byte[]> downloadAttachme(@RequestBody AttachmeQuery attachmeQuery) throws Throwable {
//        AttachmeQuery
//        //获取附件文件id
//        String attachmeId = attachmeQuery.getAttachmeId();
//        //获取附件文件名
//        String filename = attachmeQuery.getFileName();
//        logger.info("使用feign调用服务 文件下载");
//        ResponseEntity<byte[]> result = null;
//        InputStream inputStream = null;
//        try {
//            // feign文件下载
//            Response response = attachmeFeignClinent.downloadAttachme(attachmeId);
//            Response.Body body = response.body();
//            inputStream = body.asInputStream();
//            //创建字节数组，由于存放附件
//            byte[] b = new byte[inputStream.available()];
//            //输入流读取字节数组
//            inputStream.read(b);
//            /*设置响应头
//            1.设置CONTENT_DISPOSITION
//            2.设置CONTENT_TYPE
//            * */
//            HttpHeaders heads = new HttpHeaders();
//            heads.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+filename);
//            heads.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//            result = new ResponseEntity<byte[]>(b, heads, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        logger.info("附件文件下载成功");
//        return result;
//    }
//
//
//
//}
