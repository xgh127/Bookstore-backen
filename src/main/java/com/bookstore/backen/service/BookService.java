package com.bookstore.backen.service;

import com.bookstore.backen.entity.Book;

import java.util.List;

public interface BookService {
    Book getOneBookByID(Integer bookid);
    List<Book> getAllBooks();
    Book editOneBook(String url,int bookID, int price,int inventory,String bookName,String ISBN,String author,String description,String type);
    Book addOneBook(String imageUrl,int price,String bookName,int inventory,String ISBN,String author,String description,String type);
    int deleteOneBookById(Integer bookId);
}
