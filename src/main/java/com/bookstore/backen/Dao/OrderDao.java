package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Order;

import java.util.List;

public interface OrderDao {
    Order saveOneOrder(Order newOrder);
    void deletOrderByID(Integer ID);
    List<Order> getAllOrder();
}
