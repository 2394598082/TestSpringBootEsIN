package com.demo;

import com.baizhi.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestConfig extends TestES{

    @Autowired
    private Book book;
    @Test
    public void select(){
        System.out.println(book);

    }

}
