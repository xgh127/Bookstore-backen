package com.example.authorservice.Controller;

import com.example.authorservice.Entity.Book;
import com.example.authorservice.Repository.BookRepository;
import com.example.authorservice.Util.Msg.Msg;
import com.example.authorservice.Util.Msg.MsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/testBook")
    public String test(){
       Book book =  bookRepository.getOne(1);
        return "test Success"+book.getAuthor();
    }
    @GetMapping("/findAuthor/{bookName}")
    public Msg findAuthor(@PathVariable("bookName") String bookName)
    {
        List<Book> bookList = bookRepository.findAll();
        for (Book book:bookList)
        {
            if (book.getName().equals(bookName))
                return MsgUtil.makeMsg(MsgUtil.SUCCESS,book.getAuthor()) ;
        }
        return MsgUtil.makeMsg(MsgUtil.ERROR,"未查询到相关作者，请检查书名是否输入有误后重试");
    }

}
