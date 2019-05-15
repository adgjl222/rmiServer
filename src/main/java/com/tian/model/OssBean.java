package com.tian.model;

import org.springframework.stereotype.Component;

@Component(value = "ossBean")
public class OssBean {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private String bucketName;
    private String contentype;


    public String getEndpoint() {
        return endpoint;
    }


    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getContentype() {
        return contentype;
    }

    public void setContentype(String contentype) {
        this.contentype = contentype;
    }

    public OssBean() {
    }

    public OssBean(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String contentype) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
        this.contentype = contentype;
    }
}


