package com.bookstore.backen.service;

import com.bookstore.backen.entity.User;

public interface UserService {
    public User checkUser(String username, String password);
}
