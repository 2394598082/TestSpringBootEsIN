package com.baizhi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baizhi.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BookConfig {

    @Bean
    public Book book(){
        Book book = new Book();
        book.setName("这是一本好书");
        return book;
    }
    @Bean
   public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return druidDataSource;

    }
}
