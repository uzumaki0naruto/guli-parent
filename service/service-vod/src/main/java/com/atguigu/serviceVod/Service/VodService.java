package com.atguigu.serviceVod.Service;

import com.aliyuncs.exceptions.ClientException;
import com.atguigu.serviceVod.Entty.Vod;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService  {
    void removeMoreAlyVideo(List videoIdList);

    String uploadVideo(MultipartFile file) throws ClientException;
}
