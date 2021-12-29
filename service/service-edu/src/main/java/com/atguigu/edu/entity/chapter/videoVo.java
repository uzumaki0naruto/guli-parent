package com.atguigu.edu.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class videoVo {
    private String id;
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;
}
