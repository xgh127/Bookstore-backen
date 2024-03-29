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
    public Book getOneBookByID(Integer bookid){return bookDao.getOneBookByID(bookid);}

    @Override
    public List<Book> getAllBooks() {return bookDao.getAllBooks();}

    @Override
    public Book editOneBook(String url,int bookID,int price, int inventory, String bookName, String ISBN, String author, String description, String type) {
        Book book = bookDao.getOneBookByID(bookID);
        book.setImage(url);
        book.setPrice(price);
        book.setInventory(inventory);
        book.setIsbn(ISBN);
        book.setAuthor(author);
        book.setDescription(description);
        book.setType(type);
        return bookDao.saveOneBook(book);
    }

    @Override
    public Book addOneBook(String imageUrl,int price, String bookName, int inventory, String ISBN, String author, String description, String type) {
        Book newBook = new Book();
        newBook.setImage(imageUrl);
        newBook.setType(type);
        newBook.setDescription(description);
        newBook.setAuthor(author);
        newBook.setIsbn(ISBN);
        newBook.setInventory(inventory);
        newBook.setPrice(price);
        newBook.setName(bookName);
        return bookDao.saveOneBook(newBook);
    }

    @Override
    public int deleteOneBookById(Integer bookId) {
        if (bookDao.deleteOneBookById(bookId) >=0){
            return 0;
        }else{
            return -1;
        }
    }

}
