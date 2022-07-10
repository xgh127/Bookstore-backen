package com.bookstore.backen.DaoImpl;
import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    UserRepository userRepository;
    @Override
    public User checkUser(String username, String password){ return userRepository.checkUser(username,password);}

    @Override
    public List<User> getAllUser() { return userRepository.findAll();}

    @Override
    public void setUserForbiden(Integer userID, Integer status) {
        User user =  userRepository.getById(userID);
        System.out.println("userid= "+userID+user.getUsername()+user.getForbidenStatus());
        user.setForbidenStatus(status);
        userRepository.save(user);
    }

}

