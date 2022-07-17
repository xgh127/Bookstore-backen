package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.repository.CartOrderRepository;
import com.bookstore.backen.repository.OrderRepository;
import com.bookstore.backen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImp implements OrderService {
  @Autowired
  CartDao cartDao;
  @Autowired
  OrderDao orderDao;
  @Autowired
  BookDao bookDao;
    public List<Order> getUserOrder(String username){
      return orderDao.getUserOrder(username);
    }
    public int makeOrder(
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
      System.out.println(CartOrderIDGroup);
      int orderid = orderDao.saveOneOrder(newOrder).getOrderId();
      System.out.println(orderid);
      List<CartOrder> cartOrderList;
      cartOrderList = new ArrayList<>();
      for (int j : CartOrderIDGroup) {
        CartOrder cartOrder=cartDao.getCartOrderByID(j);
        cartOrder.setSubmitStatus(2);
        cartOrder.setBelongtoOrderid(orderid);
        Book book = bookDao.getOneBookByID(cartOrder.getBookid());
        int sellNum = cartOrder.getBuyNum();
        int remain = book.getInventory() - sellNum;
        int sales = book.getSellNum() + sellNum;
        if (remain < 0) return -1;
        book.setInventory(remain);
        book.setSellNum(sales);
        cartDao.saveOneCartItem(cartOrder);
        cartOrderList.add(cartOrder);
      }
      newOrder.setCartOrderList(cartOrderList);
        return  orderid;
    }

  @Override
  public void deleteOrderByID(Integer orderID) {
    orderDao.deleteOrderByID(orderID);
  }

  @Override
  public List<Order> getAllOrder() {
    return orderDao.getAllOrder();
  }

}
