package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Book;
import java.util.List;
import java.util.Optional;


public interface BookDao
{
    List<Book> getAllBooks();
   Book getOneBookByID(Integer id);
   Book saveOneBook(Book book);

   int deleteOneBookById(Integer bookId);
}
