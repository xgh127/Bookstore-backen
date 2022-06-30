package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.CartDao;
import com.bookstore.backen.entity.CartOrder;
import com.bookstore.backen.repository.CartOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CartOrderRepository cartOrderRepository;
    @Override
    public List<CartOrder> getCartOrdersByUserName(String username)
    {
        return cartOrderRepository.findAllCartItemsOfUser(username);
    }
    /*将一本书添加到购物车*/
    @Override
    public CartOrder addCartOrder(CartOrder obj)
    {
        return cartOrderRepository.save(obj);
    }
    /*从购物车改变购买数量*/
    @Override
    public CartOrder changeBuyNum(Integer cartOrderID,Integer buyNum)
    {
        CartOrder cartOrder = cartOrderRepository.getById(cartOrderID);
        cartOrder.setBuyNum(buyNum);
        return cartOrderRepository.save(cartOrder);
    }
    @Override
    public void removeItemByid(Integer cartID)
    {
         cartOrderRepository.deleteById(cartID);
    }
    @Override
    public CartOrder checkBookExist(String username,Integer bookid)
    {
        return cartOrderRepository.findOneCartItemOfUser(username,bookid);
    }
    /*更新一个CartOrder*/
    @Override
    public CartOrder saveOneCartItem(CartOrder objToSave)
    {
        return cartOrderRepository.save(objToSave);
    }

}
