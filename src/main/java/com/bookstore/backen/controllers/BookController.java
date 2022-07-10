package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.service.BookService;
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
    BookService  bookService;
    @RequestMapping(value = "/getBooks")
    public List<Book> getAllBooks() { return bookService.getAllBooks();}

    @RequestMapping(value = "/findOne")
    public Book getOneBookByID(@RequestParam(value = "id") Integer id) {return bookService.getOneBookByID(id);}

}
