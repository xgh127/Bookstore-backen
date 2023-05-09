package com.bookstore.backen.repository;

import com.bookstore.backen.entity.Order;
import net.sf.json.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value ="from Order where belongUser = :username order by orderId desc ")
    List<Order> getUserOrder(@Param("username") String username);

    @Query(value = "select belongUser ,sum(totalPrice) from Order " +
            "where  createTime >= :starttime and createTime <= :endtime " +
            "group by belongUser")
    JSONArray userConsumeStatistic(@Param(value = "starttime") Date starttime, @Param(value = "endtime") Date endtime);

    /**
     *
     * @param starttime
     * @param endtime
     * @param username
     * @return
     */
    @Query(value = "select sum(totalPrice) from Order " +
            "where belongUser = :username  and createTime >= :starttime and createTime <= :endtime " +
            "group by belongUser")
    JSONArray userSelfStatistic_BookTotalPay
            (@Param(value = "starttime") Date starttime,
             @Param(value = "endtime") Date endtime,
             @Param("username") String username);
}
