package com.bookstore.backen.service;

import com.bookstore.backen.entity.CartOrder;
import org.springframework.stereotype.Service;

public interface CartOrderService {
    public CartOrder addOneCartItem(String username,int bookid,int buyNum);
}
