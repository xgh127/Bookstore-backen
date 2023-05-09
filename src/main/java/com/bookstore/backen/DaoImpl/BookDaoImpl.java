package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao
{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() { return bookRepository.findAll(); }
    @Override
    public Book getOneBookByID(Integer id) {  return bookRepository.getById(id); }

    @Override
    public Book saveOneBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int deleteOneBookById(Integer bookId) {
        try {
            bookRepository.deleteById(bookId);
            return 0;
        }catch (Exception e)
        {
            e.printStackTrace();
            return  -1;
        }
    }

}
