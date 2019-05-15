package com.tian.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件到阿里云对象存储
 */
public interface UploadService {
    String AliyunUploadFile(MultipartFile file);
}

