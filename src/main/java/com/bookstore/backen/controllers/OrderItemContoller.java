package com.bookstore.backen.controllers;

import com.bookstore.backen.entity.Orderitem;
import com.bookstore.backen.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemContoller {
    @Autowired
    OrderItemService orderItemService;
    @GetMapping("/OrderItem/getOrderItemByUsername/{username}")
    List<Orderitem> getOrderItemByUsername(@PathVariable("username") String username){
        return orderItemService.getOrderItemByUsername(username);
    };
    @GetMapping("/OrderItem/getAllOrderItem")
    List<Orderitem> getAllOrderItem(){
        return orderItemService.getAllOrderItem();
    };
}
