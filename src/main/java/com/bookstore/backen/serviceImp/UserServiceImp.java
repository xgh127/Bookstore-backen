package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username,password);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void setUserForbiden(Integer userID, Integer status) {
            userDao.setUserForbiden(userID,status);
    }
}
