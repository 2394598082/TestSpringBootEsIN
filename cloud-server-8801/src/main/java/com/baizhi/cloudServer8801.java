package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class cloudServer8801 {
    public static void main(String[] args) {
        SpringApplication.run(cloudServer8801.class,args);
    }
}
