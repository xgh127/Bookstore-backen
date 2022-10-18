package com.bookstore.backen.service;

import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;

public interface OrderItemService {
    Orderitem insertOrderItem(Book book, Integer buyNum, Integer orderId, String belongUser) ;
}
