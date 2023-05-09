package com.bookstore.backen.DaoImpl;

import com.bookstore.backen.Dao.UserAuthDao;
import com.bookstore.backen.entity.UserAuth;
import com.bookstore.backen.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {
    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public UserAuth getUserAuthByUserId(int userId) {
        return userAuthRepository.getUserAuthByUserId(userId);
    }

    @Override
    public UserAuth getUserAuthByUsername(String username) {
        return userAuthRepository.getUserAuthByUsername(username);
    }

    @Override
    public UserAuth saveOneUserAuth(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }
}
