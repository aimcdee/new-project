package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Eureka client是客户端API，用来注册服务到Eureka server。
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProjectWechatApplication {

    public static void main(String[] args) {
        System.out.println("=============================微信端开始启动!=====================================");
        SpringApplication.run(ProjectWechatApplication.class, args);
        System.out.println("=============================微信端启动成功!=====================================");
    }
}
