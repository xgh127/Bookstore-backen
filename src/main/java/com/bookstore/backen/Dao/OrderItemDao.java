package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;

public interface OrderItemDao {
    Orderitem saveOne(Orderitem orderitem);
    int deleteOne(Integer orderItemId);
    Orderitem getOneById(Integer orderItemId);
    Orderitem insertOrderItem(Book book, Integer buyNum, Integer orderId, String belongUser) ;
}
