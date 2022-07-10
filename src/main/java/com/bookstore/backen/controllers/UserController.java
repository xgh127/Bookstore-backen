package com.bookstore.backen.controllers;

import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.constant.constant;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.service.UserService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController
{
    /*注入userDao*/
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    /*处理前端发来的登录请求，判断是都是已经注册的用户*/
    @RequestMapping(value = "/loginCheck")
    public User check(@RequestBody User userInfo)
    {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        System.out.println(username);
        System.out.println(password);
        /*如果找到从数据中找到这个用户，就返回user*/
        User user= userService.checkUser(username,password);
        if(user != null)
        {
            user.setPassword(null);
        }
        return user;
    }
    @RequestMapping("/getAllUser")
    public List<User> queryAllUserInfo(){

            List<User> resultData = userService.getAllUser();
            return resultData;
    }

    @RequestMapping("/admin/setUserLoginPermit")
    public Msg setUserLoginPermint(@RequestBody Map<String,String> params)
    {
        Integer userID  = Integer.valueOf(params.get("setUserID"));
        Integer value = Integer.valueOf(params.get("loginPermitState"));
        userService.setUserForbiden(userID,value);
        return MsgUtil.makeMsg(MsgCode.CHANGE_LOGIN_STATUS);

    }

}
