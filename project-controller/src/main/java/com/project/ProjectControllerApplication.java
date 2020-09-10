package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Eureka client是客户端API，用来注册服务到Eureka server。
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
public class ProjectControllerApplication {

    public static void main(String[] args) {
        System.out.println("=============================后台项目开始启动!=====================================");
        SpringApplication.run(ProjectControllerApplication.class, args);
        System.out.println("=============================后台项目启动成功!=====================================");
    }

}
