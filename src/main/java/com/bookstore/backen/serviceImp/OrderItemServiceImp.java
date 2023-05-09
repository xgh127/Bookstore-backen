package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.OrderItemDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;
import com.bookstore.backen.service.OrderItemService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderItemServiceImp implements OrderItemService {

@Autowired
    OrderItemDao orderItemDao;
    @Override
    @Transactional
    public Orderitem insertOrderItem(Book book, Integer buyNum, Integer orderId, String belongUser) {
        return orderItemDao.insertOrderItem(book,buyNum,orderId,belongUser);
    }

    @Override
    public List<Orderitem> getOrderItemByUsername(String username) {
        return orderItemDao.getOrderItemByUsername(username);
    }

    @Override
    public List<Orderitem> getAllOrderItem() {
        return orderItemDao.getAllOrderItem();
    }


}
