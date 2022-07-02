package com.bookstore.backen.repository;

import com.bookstore.backen.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value ="from Order where belongUser = :username")
    List<Order> getUserOrder(@Param("username") String username);
}
