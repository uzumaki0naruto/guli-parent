package com.atguigu.edu.entity.vo.front;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseFrontVo {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    private String subjectId;

    private String Price;

    private String gmt_create;

    @ApiModelProperty(value = "关注度")
    private String buy_count;



}
