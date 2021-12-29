package com.atguigu.cannal_clientedu;

import com.atguigu.cannal_clientedu.client.canalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class CannalClienteduApplication {

    @Resource
    com.atguigu.cannal_clientedu.client.canalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CannalClienteduApplication.class, args);
    }


    public void run(String... strings) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();
    }

}
