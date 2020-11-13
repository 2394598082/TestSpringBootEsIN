package com.baizhi.feign;

import org.springframework.stereotype.Service;

@Service
public class ProuctFeignServiceImpl implements ProuctFeignService {
    public String getProductId(Integer id) {

        return "商品服务失败"+id;
    }
}
