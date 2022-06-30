package com.bookstore.backen.Dao;
import com.bookstore.backen.entity.User;

public interface UserDao {
    User checkUser(String username,String password);
}
