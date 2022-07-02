package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.CartOrder;
import java.util.List;
import java.util.Map;

public interface CartDao {
    List<CartOrder> getCartOrdersByUserName(String username);
    CartOrder addCartOrder(CartOrder obj);
    CartOrder checkBookExist(String username, Integer bookid);
    CartOrder saveOneCartItem(CartOrder objToSave);
    CartOrder changeBuyNum(Integer cartOrderID,Integer buyNum);
    CartOrder changeStatus(Integer cartOrderID,Integer status);
    void removeItemByid(Integer cartID);
}

