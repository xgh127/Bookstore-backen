package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController
{
    /*注入userDao*/
    @Autowired
    UserDao userDao;
    /*处理前端发来的登录请求，判断是都是已经注册的用户*/
    @RequestMapping(value = "/loginCheck")
    public User check(@RequestBody User userInfo)
    {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        System.out.println(username);
        System.out.println(password);
        /*如果找到从数据中找到这个用户，就返回user*/
        User user= userDao.checkUser(username,password);
        if(user != null)
        {
            user.setPassword(null);
        }
        return user;
    }

}
