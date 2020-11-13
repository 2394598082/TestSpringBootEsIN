package com.demo;

import com.baizhi.dao.BookDao;
import com.baizhi.dao.CustomerBookRepository;
import com.baizhi.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TestBook extends TestES  {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CustomerBookRepository customerBookRepository;

    @Test
    public void select(){

        Iterable<Book> all = bookDao.findAll();
//        for (Book book : all) {
//            System.out.println(book);
//        }
      //  all.forEach((x)-> System.out.println(x));
        //List<Book> byAuthorAndName = bookDao.findByAuthorAndName("鲁迅", "呐喊");
//        System.out.println(byAuthorAndName);
       // Book byId = bookDao.findById("1");
      //  System.out.println(byId);
        List<Book> 鲁 = bookDao.findByName("鲁迅");
        System.out.println(鲁);
    }
    @Test
    public void selectHight(){
        List<Book> list = customerBookRepository.select(1, 1, "三");
        for (Book book : list) {

            System.out.println(book);
        }
    }
}
