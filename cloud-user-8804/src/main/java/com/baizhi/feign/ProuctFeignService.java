package com.baizhi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nacos-product",fallback = ProuctFeignServiceImpl.class)
public interface ProuctFeignService {
    @GetMapping("/product/getProductById/{id}")
    String  getProductId(@PathVariable("id")Integer id);
}
