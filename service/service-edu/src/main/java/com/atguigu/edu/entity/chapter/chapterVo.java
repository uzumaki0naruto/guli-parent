package com.atguigu.edu.entity.chapter;

import lombok.Data;

import java.util.List;
@Data
public class chapterVo {

    private String id;
    private String title;
    private List<videoVo> videoVoList;
}
