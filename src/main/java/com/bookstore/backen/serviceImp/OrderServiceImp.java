package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.BookDao;
import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.Dao.OrderDao;
import com.bookstore.backen.Dao.OrderItemDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.entity.Order;
import com.bookstore.backen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Autowired
  OrderItemDao orderItemDao;

    @Override
    public List<Order> getUserOrder(String username){
      return orderDao.getUserOrder(username);
    }

    @Override
    @Transactional
    public int makeOrder(int[] CartOrderIDGroup, String belongUser, String postcode, String phoneNumber, String destination, String receiverName, Integer totalPrice) {
      /*生成订单*/
      Order newOrder = orderDao.InsertOrder(belongUser,postcode,phoneNumber,destination,receiverName,totalPrice);

      int orderid =newOrder.getOrderId();
      System.out.println(orderid);
      /*设置书籍的库存*/
      //int res = 10 / 0;
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

        bookDao.saveOneBook(book);
        cartDao.saveOneCartItem(cartOrder);
        cartOrderList.add(cartOrder);
        try {
          orderItemDao.insertOrderItem(book,sellNum,orderid,belongUser);
          System.out.println("插入成功");
        }catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      //int res = 10 / 0;
      newOrder.setCartOrderList(cartOrderList);
        return  orderid;
    }

  @Override
  public void deleteOrderByID(Integer orderID) {
     Order order = orderDao.getOrderById(orderID);
     List<CartOrder> cartOrderList = order.getCartOrderList();
     for (CartOrder cartOrder : cartOrderList)
     cartDao.removeItemByid(cartOrder.getIdCartOrder());
    orderDao.deleteOrderByID(orderID);
  }

  @Override
  public List<Order> getAllOrder() {
    return orderDao.getAllOrder();
  }

  @Override
  public Order getOrderById(Integer id) {
    return orderDao.getOrderById(id);
  }

}
