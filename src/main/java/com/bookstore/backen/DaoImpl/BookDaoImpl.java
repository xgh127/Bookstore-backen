package com.bookstore.backen.DaoImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.repository.BookRepository;
import com.bookstore.backen.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao
{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public List<Book> getAllBooks() {

        List<Book> bookList = null;
        Object p = redisUtil.get("AllBooks");
        if(p == null){
            bookList = bookRepository.findAll();
            redisUtil.set("AllBooks", JSONArray.toJSON(bookList));
            System.out.println("通过数据库拿到AllBooks");
        }
        else {
            bookList = (List<Book>) JSONObject.parseArray(p.toString(),Book.class);
            System.out.println("通过Redis拿到AllBooks");
        }
        return bookList;
    }
    @Override
    public Book getOneBookByID(Integer id) {
        Book book = null;
        Object p = redisUtil.get("book"+id);
        if (p == null)
        {
            book = bookRepository.getById(id);
            redisUtil.set("book"+id, JSONArray.toJSON(book));
            System.out.println("通过数据库获取书籍："+book.getName());
        }else{
            book = JSONArray.parseObject(p.toString(),Book.class);
            System.out.println("通过redis缓存获取书籍："+book.getName());
        }
        return book;
    }
/*更新书籍信息，如果redis中存有书籍信息，则需要同时更新redis缓存中的数据信息，保持信息的一致性*/
    @Override
    @Transactional
    public Book saveOneBook(Book book) {
        Book book1 = bookRepository.save(book);
        Object p = redisUtil.get("book"+book.getId());
        if(p != null){
            redisUtil.set("book" + book.getId(), JSONArray.toJSON(book));
        }
        updateRedisAllBooks();
        return book1 ;
    }

    private void updateRedisAllBooks() {
        List<Book> bookList = null;
        Object p = redisUtil.get("AllBooks");
        if(p != null){
            bookList = bookRepository.findAll();
            redisUtil.set("AllBooks", JSONArray.toJSON(bookList));
            System.out.println("如果缓存中有书籍的话，更新书籍后需要更新缓存中的allBooks");
        }
    }

    /*先删除缓存再删除数据库中的书籍*/
    @Override
    @Transactional
    public void deleteOneBook(Integer bookId) {

        redisUtil.del("book"+bookId);
        bookRepository.deleteById(bookId);
        updateRedisAllBooks();
    }

}
