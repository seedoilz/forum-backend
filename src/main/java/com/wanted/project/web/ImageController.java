package com.wanted.project.web;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wanted.project.config.AliyunConfig;
import com.wanted.project.core.Result;
import com.wanted.project.core.ResultGenerator;
import com.wanted.project.model.Comment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageController {

    @CrossOrigin
    @PostMapping("/policy")
    public Result policy() {
        String accessId = AliyunConfig.accessId; // 请填写您的AccessKeyId。
        String accessKey = AliyunConfig.accessKey; // 请填写您的AccessKeySecret。
        String endpoint = AliyunConfig.endpoint; // 请填写您的 endpoint。
        String bucket = AliyunConfig.bucket; // 请填写您的 bucketname 。
        String host = AliyunConfig.host; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        // String callbackUrl = "http://88.88.88.88:8888";
        String dir = "avatar/"; // 用户上传文件时指定的前缀。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResultGenerator.genSuccessResult(respMap);
    }

    @CrossOrigin
    @PostMapping("/upload_avatar")
    public Result upload_avatar( @RequestParam("file") MultipartFile file) {
        String accessId = AliyunConfig.accessId; // 请填写您的AccessKeyId。
        String accessKey = AliyunConfig.accessKey; // 请填写您的AccessKeySecret。
        String endpoint = AliyunConfig.endpoint; // 请填写您的 endpoint。
        String bucket = AliyunConfig.bucket; // 请填写您的 bucketname 。
        String host = AliyunConfig.host; // host的格式为 bucketname.endpoint
        String dir = "avatar/"; // 用户上传文件时指定的前缀。
        String fileName = file.getOriginalFilename();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS_");
        String formattedDate = dateFormat.format(currentDate);
        String objectName = dir + formattedDate + fileName;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ossClient.putObject(bucket, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        String imageUrl = host + "/" + objectName;
        // 关闭OSSClient。
        ossClient.shutdown();
        return ResultGenerator.genSuccessResult(imageUrl);
    }

    @CrossOrigin
    @PostMapping("/upload")
    public Result upload( @RequestParam("file") MultipartFile file) {
        String accessId = AliyunConfig.accessId; // 请填写您的AccessKeyId。
        String accessKey = AliyunConfig.accessKey; // 请填写您的AccessKeySecret。
        String endpoint = AliyunConfig.endpoint; // 请填写您的 endpoint。
        String bucket = AliyunConfig.bucket; // 请填写您的 bucketname 。
        String host = AliyunConfig.host; // host的格式为 bucketname.endpoint
        String dir = "images/"; // 用户上传文件时指定的前缀。
        String fileName = file.getOriginalFilename();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS_");
        String formattedDate = dateFormat.format(currentDate);
        String objectName = dir + formattedDate + fileName;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ossClient.putObject(bucket, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        String imageUrl = host + "/" + objectName;
        // 关闭OSSClient。
        ossClient.shutdown();
        return ResultGenerator.genSuccessResult(imageUrl);
    }
}
