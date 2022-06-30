package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookDao bookDao;
    public Book getBookByID(Integer bookid){return bookDao.getOneBookByID(bookid);}

    @Override
    public List<Book> getAllBooks() {return bookDao.getAllBooks();}

}
