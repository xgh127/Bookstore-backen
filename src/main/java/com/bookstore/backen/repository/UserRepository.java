package com.bookstore.backen.repository;

import com.bookstore.backen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "from User where username = :username ")
    User checkUser(@Param("username") String username);
    @Query(value = "from User where username = :username")
    User getUserByUsername(@Param("username") String username);
}
