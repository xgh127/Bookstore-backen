package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookDao bookDao;
    @RequestMapping(value = "/getBooks")
    public List<Book> getAllBooks() { return bookDao.getAllBooks();}

    @RequestMapping(value = "/findOne")
    public Book getOneBookByID(@RequestParam(value = "id") Integer id) {return bookDao.getOneBookByID(id);}

}
