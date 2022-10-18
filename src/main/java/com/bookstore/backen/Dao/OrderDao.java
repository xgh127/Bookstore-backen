package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Order;

import java.util.List;

public interface OrderDao {
    Order saveOneOrder(Order newOrder);
    void deleteOrderByID(Integer ID);
    List<Order> getAllOrder();
    List<Order> getUserOrder(String username);
    Order getOrderById(Integer orderId);
    Order InsertOrder(
            String belongUser,
            String postcode,
            String phoneNumber,
            String destination,
            String receiverName,
            Integer totalPrice);
}
