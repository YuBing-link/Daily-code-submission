package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.utils.aliyunOSS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
public class UploadController {
    @Autowired
    aliyunOSS aliyunOSS;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("image:{}",image);
        String url= aliyunOSS.upload(image);
        return Result.success(url);
    }
}
