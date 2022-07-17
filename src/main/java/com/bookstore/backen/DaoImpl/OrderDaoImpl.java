package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
