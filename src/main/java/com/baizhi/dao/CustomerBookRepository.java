package com.baizhi.dao;

import com.baizhi.entity.Book;

import java.util.List;

public interface CustomerBookRepository {

    public List<Book> select(int page,int limit,String c);
}
