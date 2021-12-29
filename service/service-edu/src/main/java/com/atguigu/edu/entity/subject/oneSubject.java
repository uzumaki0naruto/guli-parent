package com.atguigu.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class oneSubject {
    private  String id;
    private  String title;

    private List<twoSubject> children=new ArrayList<twoSubject>();
}
