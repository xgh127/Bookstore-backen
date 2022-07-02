package com.bookstore.backen.service;

import com.bookstore.backen.entity.CartOrder;

public interface CartOrderService {
    public CartOrder addOneCartItem(String username,int bookid,int buyNum);
}
