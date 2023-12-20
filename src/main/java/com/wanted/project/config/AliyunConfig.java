package com.wanted.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
public class AliyunConfig {
    // RAM用户AccessKey ID
    @Value("${aliyun.accessId}")
    public String accessId;
    // RAM用户AccessKey
    @Value("${aliyun.accessKey}")
    public String accessKey;
    // bucket地域地址
    @Value("${aliyun.endpoint}")
    public String endpoint;
    // bucke名称
    @Value("${aliyun.bucket}")
    public String bucket;
    // 使用bucke名称 + bucket地域地址组合成前端上传地址
    @Value("${aliyun.host}")
    public String host;
}