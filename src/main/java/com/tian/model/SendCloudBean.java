package com.tian.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 引入sendCloud的配置
 */
@Component("sendCloudBean")
@Scope("singleton")
public class SendCloudBean {
    @Value("#{sendCloud.url}")
    private String url;

    @Value("#{sendCloud.apiUser}")
    private String apiUser;

    @Value("#{sendCloud.apiKey}")
    private String apiKey;


    @Value("#{sendCloud.from}")
    private String from;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getApiUser() {
        return apiUser;
    }
    public void setApiUser(String apiUser) {
        this.apiUser = apiUser;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
}
