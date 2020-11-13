package com.baizhi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/product/getProductById/{id}")
    public String getProductById(@PathVariable("id") Integer id){
        return "查到的id为"+id+"端口号为"+port;
    }
}
