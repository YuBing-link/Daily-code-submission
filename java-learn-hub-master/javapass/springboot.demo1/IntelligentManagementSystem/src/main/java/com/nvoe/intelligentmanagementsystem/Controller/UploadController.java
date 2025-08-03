package com.nvoe.intelligentmanagementsystem.Controller;

import com.nvoe.intelligentmanagementsystem.POJO.Result;
import com.nvoe.intelligentmanagementsystem.utils.aliUtils.aliyunOSS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器类
 * 处理文件上传请求，将文件上传到阿里云OSS并返回访问URL
 */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    aliyunOSS aliyunOSS;

    /**
     * 上传图片文件接口
     * 将上传的图片文件保存到阿里云OSS并返回访问URL
     * @param image 上传的图片文件
     * @return 上传结果，成功时返回文件访问URL
     * @throws Exception 文件上传过程中可能抛出的异常
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("image:{}",image);
        String url= aliyunOSS.upload(image);
        return Result.success(url);
    }
}
