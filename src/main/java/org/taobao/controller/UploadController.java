package org.taobao.controller;

import org.taobao.pojo.Result;
import org.taobao.utils.AliyunOSSOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
public class UploadController {

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 文件上传接口
     * 
     * @param file 上传的文件（接受名为file的字段）
     * @return 上传结果，包含文件URL
     */
    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            log.info("接收到文件上传请求");
            
            if (file == null || file.isEmpty()) {
                log.error("上传文件为空");
                return Result.error("上传文件不能为空");
            }
            
            log.info("上传文件名：{}，大小：{}字节", file.getOriginalFilename(), file.getSize());
            
            // 调用OSS上传工具
            String ossUrl = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
            
            log.info("文件上传成功，URL：{}", ossUrl);
            return Result.success(ossUrl);
        } catch (Exception e) {
            log.error("文件上传失败：", e);
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }
    
    /**
     * 支持多文件上传的接口
     * 
     * @param files 上传的文件数组
     * @return 上传结果，包含多个文件URL
     */
    @PostMapping("/upload/multiple")
    public Result<String[]> uploadMultipleFiles(@RequestPart(value = "files", required = false) MultipartFile[] files) {
        try {
            log.info("接收到多文件上传请求");
            
            if (files == null || files.length == 0) {
                log.error("上传文件数组为空");
                return Result.error("上传文件不能为空");
            }
            
            log.info("上传文件数量：{}", files.length);
            
            String[] ossUrls = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                if (file == null || file.isEmpty()) {
                    log.error("第{}个文件为空，跳过", i+1);
                    ossUrls[i] = null;
                    continue;
                }
                
                log.info("第{}个文件：{}，大小：{}字节", i+1, file.getOriginalFilename(), file.getSize());
                ossUrls[i] = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
                log.info("第{}个文件上传成功，URL：{}", i+1, ossUrls[i]);
            }
            
            return Result.success(ossUrls);
        } catch (Exception e) {
            log.error("多文件上传失败：", e);
            return Result.error("多文件上传失败：" + e.getMessage());
        }
    }
}