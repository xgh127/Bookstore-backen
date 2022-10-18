package com.bookstore.backen.repository;

import com.bookstore.backen.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Orderitem,Integer> {
}
