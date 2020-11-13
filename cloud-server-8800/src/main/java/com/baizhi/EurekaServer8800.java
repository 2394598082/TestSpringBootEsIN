package com.baizhi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer8800 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8800.class,args);
    }
}
