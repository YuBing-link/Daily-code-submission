package com.nvoe.intelligentmanagementsystem.utils;

import com.aliyun.oss.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.util.UUID;
@Component
public class aliyunOSS {
    @Autowired
    ali a;
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    String endpoint ;
    // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
    String accessKeyId ; // 替换为您的AccessKeyId
    String accessKeySecret ;
    String bucketName ;


    public String upload(MultipartFile file) throws Exception {
        endpoint = a.getEndpoint();
        accessKeyId = a.getAccessKeyId();
        accessKeySecret = a.getAccessKeySecret();
        bucketName = a.getBucketName();
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID()+ originalFilename.substring(originalFilename.lastIndexOf("."));
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);
        String url = endpoint.split("//")[0]+bucketName+"."+endpoint.split("//")[1]+"/"+fileName;
        ossClient.shutdown();
        return url;
    }

}