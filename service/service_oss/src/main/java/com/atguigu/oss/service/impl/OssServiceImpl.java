package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
@CrossOrigin
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) throws IOException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId =  ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret =  ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String buckName=ConstantPropertiesUtils.BUCKET_NAME;

//获取文件名称
        String originalFilename = file.getOriginalFilename();
//1.在文件名称里面添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
//        2.把文件按照日期分类
//        2019/11/12/01.jpg
//        获取当前日期
        String dataPath = new DateTime().toString("yyyy/MM/dd");
        String fileName=dataPath+"/"+uuid+originalFilename;


// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = file.getInputStream();
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(buckName, fileName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
//        把需要上传到阿里云oss路径手动拼接出来
        String url="https://"+buckName+"."+endpoint+"/"+fileName;
        return url;
    }
}
