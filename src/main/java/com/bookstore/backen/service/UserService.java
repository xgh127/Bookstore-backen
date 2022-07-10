package com.bookstore.backen.service;

import com.bookstore.backen.entity.User;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);
    List<User> getAllUser();
    void setUserForbiden(Integer userID,Integer status);
}
