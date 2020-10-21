package com.baizhi.dao;

import com.baizhi.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookDao extends ElasticsearchRepository<Book,Long> {
    List<Book> findByName(String name);
    List<Book> findByAuthorAndName(String author,String name);
    Book findById(String id);
}
