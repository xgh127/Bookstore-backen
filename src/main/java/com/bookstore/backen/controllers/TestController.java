package com.bookstore.backen.controllers;

import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.esBook;
import com.bookstore.backen.repository.BookRepository;
import com.bookstore.backen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bookstore.backen.serviceImp.BookServiceImp.ToEsBook;

/**
 *
 * 编码测试
* */
@RestController
public class TestController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    com.bookstore.backen.repository.EsBookRepository esBookRepository;


    // 初始化，将书籍迁移到es中
    @RequestMapping("/init")
    public List<esBook> init(){
        esBookRepository.deleteAll();
        List<Book> allBooks = bookRepository.findAll();
        for (Book allBook : allBooks) {
            esBook tmpES = ToEsBook(allBook);
            esBookRepository.save(tmpES);
        }
        return esBookRepository.findAll();
    }

}
