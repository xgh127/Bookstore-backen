package com.bookstore.backen.repository;

import com.bookstore.backen.entity.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*无脑这样写就是了*/
public interface CartOrderRepository extends JpaRepository<CartOrder,Integer>
{ /*jpa能自动创建查询代码，只需要在这里实现一个接口就行了*/
    /*查询某个用户购物车内的所有书籍*/
    @Query(value ="from CartOrder where belongtoUser = :username and (submitStatus = 0 or submitStatus =1)")
    List<CartOrder> findAllCartItemsOfUser(@Param("username") String username);
    /*查询某本书是否在购物车内*/
    @Query(value ="from CartOrder where belongtoUser = :username and bookid = :bookid and (submitStatus =0 or submitStatus =1)")
    CartOrder findOneCartItemOfUser(@Param("username") String username,@Param("bookid") Integer bookid);
}
