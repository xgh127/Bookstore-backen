package com.bookstore.backen.service;

import com.bookstore.backen.entity.User;
import com.bookstore.backen.utils.Msg.Msg;

import java.util.List;

public interface UserService {
    Msg checkUser(String username, String password);
    User modifyUserInfo(String username,String nickname,String email,String tel,String description);
    String logout();
    List<User> getAllUser();
    void setUserForbidden(Integer userID, Integer status);
    int register(String nickname,String username,String password,String email,String phone,String description,int gender);
}
