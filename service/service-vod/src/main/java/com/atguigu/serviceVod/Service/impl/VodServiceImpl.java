package com.atguigu.serviceVod.Service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.exceptionhandler.guliException;
import com.atguigu.serviceVod.Service.VodService;
import com.atguigu.serviceVod.Utils.InitVodCilent;
import com.atguigu.serviceVod.Utils.util;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class VodServiceImpl  implements  VodService{



    @Override
    public  void removeMoreAlyVideo(List videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(util.ACCESS_KEY_ID, util.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new guliException(20001,"删除视频失败");
        }

    }

    @Override
    public String uploadVideo(MultipartFile file) throws ClientException {
        String fileName = file.getOriginalFilename();
        String title=fileName.substring(0,fileName.lastIndexOf("."));
        try {
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request =
   new UploadStreamRequest(util.ACCESS_KEY_ID,util.ACCESS_KEY_SECRET,title,fileName,inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
