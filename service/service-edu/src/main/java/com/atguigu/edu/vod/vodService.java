package com.atguigu.edu.vod;

import com.atguigu.commontuils.R;
import org.springframework.stereotype.Component;

import java.util.List;

//服务熔断处理方法
@Component
public class vodService implements vodClient{



    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
