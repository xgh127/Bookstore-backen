package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void deletOrderByID(Integer ID)
    {
        orderRepository.deleteById(ID);
    }
}
