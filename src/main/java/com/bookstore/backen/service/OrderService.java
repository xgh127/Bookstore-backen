package com.bookstore.backen.service;

import com.bookstore.backen.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    List<Order> getUserOrder(String username);
    int makeOrder(
            int[] CartOrderIDGroup,
            String belongUser,
            String postcode,
            String phoneNumber,
            String destination,
            String receiverName,
            Integer totalPrice
    );
    void deleteOrderByID(Integer orderID);
    List<Order> getAllOrder();
}
