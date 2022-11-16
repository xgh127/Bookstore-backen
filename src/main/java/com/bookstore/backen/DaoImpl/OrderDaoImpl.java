package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order saveOneOrder(Order newOrder)
    {
       return  orderRepository.save(newOrder);
    }
    @Override
    public void deleteOrderByID(Integer ID)
    {
        orderRepository.deleteById(ID);
    }
    @Override
    public List<Order> getAllOrder() { return orderRepository.findAll();}

    @Override
    public List<Order> getUserOrder(String username) {
        return orderRepository.getUserOrder(username);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderRepository.getOne(orderId);
    }

    @Override
    @Transactional
    public Order InsertOrder(String belongUser, String postcode, String phoneNumber, String destination, String receiverName, Integer totalPrice) {
        Order newOrder = new Order();
        newOrder.setBelongUser(belongUser);
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        newOrder.setCreateTime(curTime);
        newOrder.setPostcode(postcode);
        newOrder.setPhoneNumber(phoneNumber);
        newOrder.setDestination(destination);
        newOrder.setReceiverName(receiverName);
        newOrder.setTotalPrice(totalPrice);
       // int res = 10 / 0;
        return orderRepository.save(newOrder);
    }

}
