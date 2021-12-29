package com.atguigu.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class courseVo {

    @ApiModelProperty(value = "课程id")
    private String id;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    //    如果没有一级分类则值为0
    @ApiModelProperty(value = "一级分类的id号")
    private String subjectParentId;

    @ApiModelProperty(value = "二级分类ID")
    private String subjectId;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程销售价格，为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "封面路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;


}
