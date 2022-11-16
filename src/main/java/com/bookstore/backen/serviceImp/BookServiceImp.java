package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.EsBookDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.esBook;
import com.bookstore.backen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private EsBookDao esBookDao;
    public Book getOneBookByID(Integer bookid){return bookDao.getOneBookByID(bookid);}

    @Override
    public List<Book> getAllBooks() {return bookDao.getAllBooks();}

    @Override
    public Book editOneBook(int bookID,int price, int inventory, String bookName, String ISBN, String author, String description, String type) {
        Book book = bookDao.getOneBookByID(bookID);
        book.setPrice(price);
        book.setInventory(inventory);
        book.setIsbn(ISBN);
        book.setAuthor(author);
        book.setDescription(description);
        book.setType(type);
        return bookDao.saveOneBook(book);
    }

    @Override
    public Book addOneBook(int price, String bookName, int inventory, String ISBN, String author, String description, String type) {
        Book newBook = new Book();
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
    public int deleteOneBook(Integer bookId) {
        try{
            bookDao.deleteOneBook(bookId);
            return 0;
        }catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 根据简介搜索
     * @param type
     * @param keyword
     * @return
     */
    @Override
    public List<Book> SearchedBooks(int type, String keyword) {
        keyword = "%"+keyword+"%";
        SearchHits<esBook> bookSearchHits = esBookDao.findEsBooksByDescription(keyword);
        return EsToBook(bookSearchHits);
    }

    public static esBook ToEsBook(Book book) {
        esBook newESBook = new esBook();
        newESBook.setId(book.getId());
        newESBook.setAuthor(book.getAuthor());
        newESBook.setDescription(book.getDescription());
        newESBook.setImage(book.getImage());
        newESBook.setInventory(book.getInventory());
        newESBook.setIsbn(book.getIsbn());
        newESBook.setName(book.getName());
        newESBook.setPrice(book.getPrice());
        newESBook.setSellnum(book.getSellNum());
        newESBook.setType(book.getType());
        newESBook.setTitle(book.getTitle());

        return newESBook;
    }
    private List<Book> EsToBook(SearchHits<esBook> searchHits){
        List<Book> books = new ArrayList<>();
        for (SearchHit<esBook> hit : searchHits){
            esBook esBook = hit.getContent();
            Book book = new Book();
            book.setAuthor(esBook.getAuthor());
            book.setName(esBook.getName());
            book.setTitle(esBook.getTitle());
            book.setDescription(String.valueOf(hit.getHighlightField("description")));
            book.setInventory(esBook.getInventory());
            book.setIsbn(esBook.getIsbn());
            book.setImage(esBook.getImage());
            book.setSellNum(esBook.getSellnum());
            book.setPrice(esBook.getPrice());
            book.setId(esBook.getId());
            book.setType(esBook.getType());
            books.add(book);
        }
        return books;
    }

}
