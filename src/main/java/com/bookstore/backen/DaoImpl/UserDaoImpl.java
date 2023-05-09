package com.bookstore.backen.DaoImpl;
import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.repository.UserAuthRepository;
import com.bookstore.backen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public List<User> getAllUser() { return userRepository.findAll();}

    @Override
    public User findUserByUserName(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void setUserForbidden(Integer userID, Integer status) {
//        User user =  userRepository.getById(userID);
//        if (user.getUserAuth().getUserType() == 0) return;
//        System.out.println("userid= "+userID+user.getUsername()+user.getUserAuth().getForbiddenStatus());
//        user.getUserAuth().setForbiddenStatus(status);
//        userRepository.save(user);
    }

    @Override
    public User saveOneUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

}

