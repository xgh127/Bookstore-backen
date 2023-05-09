package com.bookstore.backen.repository;

import com.bookstore.backen.entity.Orderitem;
import net.sf.json.JSONArray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<Orderitem,Integer> {
    /**
     * 用户获取自己的OrderItem列表
     * @param username
     * @return
     */
    @Query(value = "from Orderitem  where  belongUser = :username order by orderItemId desc ")
    List<Orderitem> getOrderitemByUsername(@Param("username") String username);

    /**
     * 管理员获取一段时间内书籍的销售数量
     * @param starttime
     * @param endtime
     * @return
     */
    @Query(value = "select bookId,sum(buyNum) from Orderitem " +
            "where  finsihTime >= :starttime and finsihTime <= :endtime " +
            "group by bookId")
    JSONArray bookSellStatistic(@Param(value = "starttime") Date starttime, @Param(value = "endtime") Date endtime);

    /**
     * 用户统计书籍和购买数量
     * @param starttime
     * @param endtime
     * @param username
     * @return
     */
    @Query(value = "select bookId,sum(buyNum) from Orderitem " +
            "where belongUser = :username  and finsihTime >= :starttime and finsihTime <= :endtime " +
            "group by bookId")
    JSONArray userSelfStatistic_BookWithBuyNum
            (@Param(value = "starttime") Date starttime,
             @Param(value = "endtime") Date endtime,
             @Param("username") String username);

    /**
     * 用户统计书籍总的数量
     * @param starttime
     * @param endtime
     * @param username
     * @return
     */
    @Query(value = "select sum(buyNum) from Orderitem " +
            "where belongUser = :username and finsihTime >= :starttime and finsihTime <= :endtime " +
            "group by belongUser")
    JSONArray userSelfStatistic_BookAllBuyNum
            (@Param(value = "starttime") Date starttime,
             @Param(value = "endtime") Date endtime,
             @Param("username") String username);

}
