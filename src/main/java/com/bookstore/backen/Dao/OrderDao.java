package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Order;
import net.sf.json.JSONArray;

import java.util.Date;
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
    /*数据统计*/
    JSONArray userConsumeStatistic(Date startTime, Date endTime);
    JSONArray userSelfStatistic_BookTotalPay(Date starttime, Date endtime, String username);
}
