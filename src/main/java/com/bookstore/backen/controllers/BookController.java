package com.bookstore.backen.controllers;

import com.bookstore.backen.constant.constant;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.service.BookService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class BookController {
  @Autowired
    BookService  bookService;
    @RequestMapping(value = "/getBooks")
    public List<Book> getAllBooks() { return bookService.getAllBooks();}

    @RequestMapping(value = "/findOne")
    public Book getOneBookByID(@RequestParam(value = "id") Integer id) {return bookService.getOneBookByID(id);}
    @RequestMapping(value ="/editOneBook")
    public Msg editOneBook(@RequestBody Map<String,String> info)
    {
      JSONObject auth = SessionUtil.getAuth();
      // 检查是全局管理员，才允许获得签名
      if(auth == null || !Objects.equals(auth.get(constant.PRIVILEGE),0))
        return null;
      try {
        System.out.println(info);
        int bookID = Integer.parseInt(info.get("bookID"));
        double price = Double.parseDouble((info.get("price")));
        int storePrice = (int) (price*100);
        int inventory = Integer.parseInt(info.get("inventory"));
        String url = info.get("url");
        String bookName = info.get("bookName");
        String ISBN = info.get("ISBN");
        String author = info.get("author");
        String description = info.get("description");
        String type = info.get("type");
        Book book = bookService.editOneBook(url,bookID,storePrice,inventory,bookName,ISBN,author,description,type);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS,"修改书籍信息成功", JSONObject.fromObject(book));
      }
      catch (Error error)
      {
        return MsgUtil.makeMsg(MsgUtil.ERROR,"修改书籍信息出错！");
      }

    }
    @RequestMapping(value = "/AddOneBook")
    public Msg AddOneBook(@RequestBody Map<String,String> bookInfo)
    {
      JSONObject auth = SessionUtil.getAuth();
      // 检查是全局管理员，才允许获得签名
      if(auth == null || !Objects.equals(auth.get(constant.PRIVILEGE),0))
        return null;
      double price = Double.parseDouble(bookInfo.get("price"));
      int storePrice =(int) price*100;
      String imageUrl = bookInfo.get("imageUrl");
      String bookName = bookInfo.get("bookName");
      int inventory = Integer.parseInt(bookInfo.get("inventory"));
      String ISBN = bookInfo.get("ISBN");
      String author = bookInfo.get("author");
      String description = bookInfo.get("description");
      String type = bookInfo.get("type");
      try {
        bookService.addOneBook(imageUrl,storePrice,bookName,inventory,ISBN,author,description,type);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS,"添加书籍成功");
      }catch (Exception e) {
        return MsgUtil.makeMsg(MsgUtil.ERROR,"添加书籍异常");
      }

    }
    @RequestMapping("/deleteOneBook/{bookId}")
  public Msg deleteOneBook(@PathVariable("bookId") Integer bookId)
    {
      if (bookService.deleteOneBookById(bookId) >= 0){
        return  MsgUtil.makeMsg(MsgUtil.SUCCESS,"删除书籍成功");
      }else {
        return MsgUtil.makeMsg(MsgUtil.ERROR,"删除书籍异常");
      }

    }

}
