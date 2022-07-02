package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Order;

public interface OrderDao {
    public Order saveOneOrder(Order newOrder);
    public void deletOrderByID(Integer ID);
}
