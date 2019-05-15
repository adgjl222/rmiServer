package com.tian.util;

import com.tian.model.SendCloudBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.HttpStatus;
import ytx.org.apache.http.NameValuePair;
import ytx.org.apache.http.client.HttpClient;
import ytx.org.apache.http.client.entity.UrlEncodedFormEntity;
import ytx.org.apache.http.client.methods.HttpPost;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.message.BasicNameValuePair;
import ytx.org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendCloud {
    public static String sendCommon(String email, String msgCode) throws IOException{



        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");


        SendCloudBean sendCloudBean = ctx.getBean(SendCloudBean.class);

        String subject = "jnshu邮箱注册验证码";
        String html = "验证码: "+msgCode+", 10分钟内有效,请勿告诉其他人！";

        HttpPost httpPost = new HttpPost(sendCloudBean.getUrl());
        HttpClient httpClient = new DefaultHttpClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", sendCloudBean.getApiUser()));
        params.add(new BasicNameValuePair("apiKey", sendCloudBean.getApiKey()));
        params.add(new BasicNameValuePair("to", email));
        params.add(new BasicNameValuePair("from", "1938742950@qq.com"));
        params.add(new BasicNameValuePair("fromName", "jnshu测试"));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", html));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);

        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 正常返回, 解析返回数据

            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.err.println("error");
            return  "false";
        }
        httpPost.releaseConnection();
        return "true";
    }


    public static void main(String[] args) throws Exception {

        // 随机生成6位验证码，测试
        String msgCode = DataUtils.getNumber(6);
        System.out.println("msgCode = " + msgCode);

        String email = "测试邮箱";
        msgCode = sendCommon(email,msgCode);
        System.out.println("msgCode = " + msgCode);

    }


}
