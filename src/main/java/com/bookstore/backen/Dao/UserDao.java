package com.bookstore.backen.Dao;
import com.bookstore.backen.entity.User;

import java.util.List;

public interface UserDao {
    User checkUser(String username,String password);
    List<User> getAllUser();
    void setUserForbiden(Integer userID,Integer status);
}
