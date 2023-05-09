package com.bookstore.backen.service;

import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;
import net.sf.json.JSONArray;

import java.util.Date;
import java.util.List;

public interface OrderItemService {
    Orderitem insertOrderItem(Book book, Integer buyNum, Integer orderId, String belongUser) ;
    List<Orderitem> getOrderItemByUsername(String username);

    /**
     * 获取所有的OrderItem
     * @return
     */
    List<Orderitem> getAllOrderItem();

}
