package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Book;

import java.util.List;


public interface BookDao
{
    List<Book> getAllBooks();
   Book getOneBookByID(Integer id);
   Book saveOneBook(Book book);
   void deleteOneBook(Integer bookId);
}
