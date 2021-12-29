package com.atguigu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConstantPropertiesUtils2 {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.KeyId}")
    private String KeyId;

    @Value("${aliyun.oss.file.KeySecret}")
    private  String KeySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

}
