package com.bookstore.backen.Dao;

import com.bookstore.backen.entity.UserAuth;

public interface UserAuthDao {
    UserAuth getUserAuthByUserId(int userId);
    UserAuth getUserAuthByUsername(String username);
    //保存UserAuth
    UserAuth saveOneUserAuth(UserAuth userAuth);
}
