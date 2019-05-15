package com.tian.service.impl;

import com.tian.service.UploadService;
import com.tian.util.AliyunOss;
import com.tian.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

    private  static final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

    // 默认本地保存路径
    private String saveLocalPath = "./upload/";
    // 默认文件保存路径--------------指的是阿里云对象存储的路径
    private String savePath = "ss/";

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }



    @Override
    public String AliyunUploadFile(MultipartFile file) {
        // 获取文件名称
        String filename = file.getOriginalFilename();

        // 生成存储路径
        String savePrePath = saveLocalPath+savePath;
        // 获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 存储目录
        File fileDir = new File(savePrePath);
        // 判断目录是否存在
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        // 生成存储文件
        String fileSaveName = UUIDUtils.getUUID()+String.valueOf(System.currentTimeMillis())+suffix;
        try{
            // 存储路径和存储文件生成新的临时文件
            File saveFile = new File(savePrePath,fileSaveName);
            // 移动临时文件
            file.transferTo(saveFile);
            // 准备上传文件到阿里云
            AliyunOss ossClientUtil = new AliyunOss();
            String fileUrl = ossClientUtil.uploadFile(savePrePath+fileSaveName,savePath+fileSaveName);
            saveFile.delete();

            return fileUrl;
        } catch (IOException e){
            e.printStackTrace();
            log.warn("上传头像至阿里云oss发生异常，失败");
        }
        return null;



    }
}
