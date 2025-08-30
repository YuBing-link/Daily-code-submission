package com.nvoe.aliyunosstest.Controller;

import com.nvoe.aliyunoss.AliyunOSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Autowired
    AliyunOSS aliyunOSS;
    
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception{
        String url = aliyunOSS.upload(file);
        return url;
    }
}