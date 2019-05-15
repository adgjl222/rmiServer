package com.tian.util;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.tian.model.OssBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AliyunOss {
    private  static final Logger log = LoggerFactory.getLogger(AliyunOss.class);

//    @Resource
//    OssBean ossBean;


    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    OssBean ossBean = (OssBean) applicationContext.getBean(OssBean.class);

    private OSSClient oSSClient;

    public AliyunOss() {
        oSSClient = new OSSClient(ossBean.getEndpoint(),ossBean.getAccessKeyId(), ossBean.getAccessKeySecret());
    }

    /**
     * aliyun 简易上传工具
     * @param uploadFile 待上传的本地文件路径
     * @param savePathname 保存到对象存储的路径
     * @return 图片的url
     */
    public String uploadFile(String uploadFile,String savePathname){
        log.info("oss开始上传......");
        OSSClient ossClient = new OSSClient(ossBean.getEndpoint(), ossBean.getAccessKeyId(), ossBean.getAccessKeySecret());
        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(ossBean.getBucketName(), savePathname);
            // 待上传的本地文件
            uploadFileRequest.setUploadFile(uploadFile);
            // 设置并发下载量，默认1
            uploadFileRequest.setTaskNum(5);
            // 设置分片大小，默认1000Kb
            uploadFileRequest.setPartSize(1024 * 1024 * 1);
            // 开启断点续传，默认关闭

            uploadFileRequest.setEnableCheckpoint(true);

            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);

            CompleteMultipartUploadResult multipartUploadResult =
                    uploadResult.getMultipartUploadResult();
            log.info("数据中心"+multipartUploadResult.getLocation());
            return  multipartUploadResult.getLocation();

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return null;
    }


}
