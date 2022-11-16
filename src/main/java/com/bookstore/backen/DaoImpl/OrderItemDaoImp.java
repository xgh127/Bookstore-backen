package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.OrderItemDao;
import com.bookstore.backen.entity.Book;
import com.bookstore.backen.entity.Orderitem;
import com.bookstore.backen.repository.OrderItemRepository;
import com.bookstore.backen.utils.TimeUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class OrderItemDaoImp implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Override
    public Orderitem saveOne(Orderitem orderitem) {
        return orderItemRepository.save(orderitem);
    }

    @Override
    public int deleteOne(Integer orderItemId) {
        orderItemRepository.deleteById(orderItemId);
        return 0;
    }

    @Override
    public Orderitem getOneById(Integer orderItemId) {
        return orderItemRepository.getOne(orderItemId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Orderitem insertOrderItem(Book book, Integer buyNum, Integer orderId, String belongUser) {
        Orderitem orderitem = new Orderitem();
        orderitem.setBelongOrderId(orderId);
        orderitem.setAuthor(book.getAuthor());
        orderitem.setBelongUser(belongUser);
        orderitem.setBookId(book.getId());
        orderitem.setDescription(book.getDescription());
        orderitem.setImage(book.getImage());
        orderitem.setIsbn(book.getIsbn());
        orderitem.setName(book.getName());
        orderitem.setType(book.getType());
        orderitem.setPrice(book.getPrice());
        orderitem.setFinsihTime(TimeUtl.getNow());
        orderitem.setBuyNum(buyNum);
        System.out.println("插入orderItem"+book.getName());
        //int res = 10 / 0;
        return orderItemRepository.save(orderitem);
    }
}
