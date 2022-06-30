package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username,password);
    }
}
