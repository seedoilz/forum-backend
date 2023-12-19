package com.wanted.project.config;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;

public class AliyunConfig {
    // RAM用户AccessKey ID
    @Value("${aliyun.accessId}")
    public static String accessId;
    // RAM用户AccessKey
    @Value("${aliyun.accessKey}")
    public static String accessKey;
    // bucket地域地址
    @Value("${aliyun.endpoint}")
    public static String endpoint;
    // bucke名称
    @Value("${aliyun.bucket}")
    public static String bucket;
    // 使用bucke名称 + bucket地域地址组合成前端上传地址
    @Value("${aliyun.host}")
    public static String host;
}