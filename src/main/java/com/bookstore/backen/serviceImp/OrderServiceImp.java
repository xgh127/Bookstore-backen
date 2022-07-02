package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.repository.CartOrderRepository;
import com.bookstore.backen.repository.OrderRepository;
import com.bookstore.backen.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImp implements OrderService {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CartOrderRepository cartOrderRepository;
  @Autowired
  OrderDao orderDao;
    public List<Order> getUserOrder(String username){
      return orderRepository.getUserOrder(username);
    }
    public void makeOrder(
            int[] CartOrderIDGroup,
            String belongUser,
            String postcode,
            String phoneNumber,
            String destination,
            String receiverName,
            Integer totalPrice) {
      System.out.println("enter makeorder");
      Order newOrder = new Order();
      newOrder.setBelongUser(belongUser);
      Timestamp curTime = new Timestamp(System.currentTimeMillis());
      newOrder.setCreateTime(curTime);
      newOrder.setPostcode(postcode);
      newOrder.setPhoneNumber(phoneNumber);
      newOrder.setDestination(destination);
      newOrder.setReceiverName(receiverName);
      newOrder.setTotalPrice(totalPrice);

      int orderid = orderDao.saveOneOrder(newOrder).getOrderId();
      System.out.println(orderid);
      List<CartOrder> cartOrderList;
      cartOrderList = new ArrayList<>();
      for (int j : CartOrderIDGroup) {
        CartOrder cartOrder=cartOrderRepository.getById(j);
        cartOrder.setSubmitStatus(2);
        cartOrder.setBelongtoOrderid(orderid);
        cartOrderRepository.save(cartOrder);
        cartOrderList.add(cartOrder);
      } newOrder.setCartOrderList(cartOrderList);

    }

}
