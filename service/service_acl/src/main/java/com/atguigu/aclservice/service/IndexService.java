package com.atguigu.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface IndexService {

   Map<String,Object>   getUserInfo(String username);


   List<String> getMenu(String username);
}
