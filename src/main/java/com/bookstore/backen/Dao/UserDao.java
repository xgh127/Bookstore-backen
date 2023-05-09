package com.bookstore.backen.Dao;
import com.bookstore.backen.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    User findUserByUserName(String username);
    void setUserForbidden(Integer userID, Integer status);
    User saveOneUser(User user);
    User getUserByUsername(String username);
}
