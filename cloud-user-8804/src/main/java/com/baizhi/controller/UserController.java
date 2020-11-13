package com.baizhi.controller;

import com.baizhi.feign.ProuctFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProuctFeignService feignService;

    @RequestMapping("/order/getUserInfo")
    public String getUserInfo(){
        //远程调用商品服务
      //  String url = "http://nacos-product/product/getProductById?id=1";
        String productId = feignService.getProductId(1);
       // String forObject = restTemplate.getForObject(url, String.class);
        return "用户服务,查询商品服务结果为-->"+productId;

    }
}
