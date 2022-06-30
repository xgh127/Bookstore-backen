package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.service.CartOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class CartOrderServiceImp implements CartOrderService {
    @Autowired
    BookDao bookDao;
    @Autowired
    CartDao cartDao;

    /*添加一个书籍到购物车*/
    public CartOrder addOneCartItem(String username,int bookid,int buyNum)
    {
        CartOrder tmp = cartDao.checkBookExist(username,bookid);
        if(tmp != null)
        {
            Integer newbuyNum = tmp.getBuyNum()+buyNum;
            tmp.setBuyNum(newbuyNum);
            cartDao.saveOneCartItem(tmp);
            return tmp;
        }
        else {
            Book book = bookDao.getOneBookByID(bookid);
            String bookName = book.getName();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            CartOrder newCartOrder = new CartOrder();
            newCartOrder.setPrice(book.getPrice());
            newCartOrder.setBookid(bookid);
            newCartOrder.setBookName(bookName);
            newCartOrder.setBuyNum(buyNum);
            newCartOrder.setBelongtoUser(username);
            newCartOrder.setSubmitStatus(0);
            newCartOrder.setCreateTime(currentTime);
            cartDao.addCartOrder(newCartOrder);
            return newCartOrder;
        }
    }
}
