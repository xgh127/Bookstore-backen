package com.bookstore.backen.service;

import com.bookstore.backen.entity.Book;

import java.util.List;

public interface BookService {
    Book getBookByID(Integer bookid);
    List<Book> getAllBooks();
}
