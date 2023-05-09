package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;
import net.sf.json.JSONArray;

import java.util.Date;
import java.util.List;

public interface OrderItemDao {
    Orderitem saveOne(Orderitem orderitem);
    int deleteOne(Integer orderItemId);
    Orderitem getOneById(Integer orderItemId);
    Orderitem insertOrderItem(Book book, Integer buyNum, Integer orderId, String belongUser) ;
    List<Orderitem> getOrderItemByUsername(String username);
    List<Orderitem> getAllOrderItem();
    JSONArray bookSellStatistic(Date starttime, Date endtime);
    JSONArray userSelfStatistic_BookWithBuyNum(Date starttime, Date endtime, String username);

    JSONArray userSelfStatistic_BookAllBuyNum(Date starttime, Date endtime, String username);


}
