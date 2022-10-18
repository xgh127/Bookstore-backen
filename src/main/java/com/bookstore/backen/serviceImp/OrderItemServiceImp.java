package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.OrderItemDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;
import com.bookstore.backen.service.OrderItemService;
import com.bookstore.backen.utils.TimeUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class OrderItemServiceImp implements OrderItemService {

@Autowired
    OrderItemDao orderItemDao;
    @Override
    @Transactional
    public Orderitem insertOrderItem(Book book, Integer buyNum,Integer orderId,String belongUser) {
        return orderItemDao.insertOrderItem(book,buyNum,orderId,belongUser);
    }
}
