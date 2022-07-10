package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.service.CartOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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
        { System.out.println("add ???????"+tmp.getBookName());
            Integer newbuyNum = tmp.getBuyNum()+buyNum;
            tmp.setBuyNum(newbuyNum);
            cartDao.saveOneCartItem(tmp);
            return tmp;
        }
        else {
            Book book = bookDao.getOneBookByID(bookid);
            System.out.println("add"+book.getName());
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

    @Override
    public void removeItemByid(Integer cartID) {
        cartDao.removeItemByid(cartID);
    }

    @Override
    public CartOrder changeStatus(Integer cartOrderID, Integer status) {
        return cartDao.changeStatus(cartOrderID,status);
    }

    @Override
    public CartOrder changeBuyNum(Integer cartOrderID, Integer buyNum) {
        return cartDao.changeBuyNum(cartOrderID,buyNum);
    }

    @Override
    public CartOrder getCartOrderByID(Integer id) {
        return cartDao.getCartOrderByID(id);
    }

    @Override
    public CartOrder checkBookExist(String username, Integer bookid) {
        return cartDao.checkBookExist(username,bookid);
    }

    @Override
    public List<CartOrder> getCartOrdersByUserName(String username) {
        return cartDao.getCartOrdersByUserName(username);
    }
}
