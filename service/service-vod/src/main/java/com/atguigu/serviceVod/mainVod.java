package com.atguigu.serviceVod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.CrossOrigin;



@ComponentScan(basePackages = {"com.atguigu"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient

public class mainVod {
    public static void main(String[] args) {
        SpringApplication.run(mainVod.class,args);
    }
}
