package com.bookstore.backen.service;

import com.bookstore.backen.entity.CartOrder;

import java.util.List;

public interface CartOrderService {
    CartOrder addOneCartItem(String username, int bookid, int buyNum);
    void removeItemByid(Integer cartID);
    CartOrder changeStatus(Integer cartOrderID,Integer status);
    CartOrder changeBuyNum(Integer cartOrderID,Integer buyNum);
    CartOrder getCartOrderByID(Integer id);
    CartOrder checkBookExist(String username, Integer bookid);
    List<CartOrder> getCartOrdersByUserName(String username);
}
