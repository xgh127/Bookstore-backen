package com.bookstore.backen.controllers;

import com.bookstore.backen.entity.User;
import com.bookstore.backen.service.UserService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController
{
    @Autowired
    UserService userService;
    /*处理前端发来的登录请求，判断是都是已经注册的用户*/

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
        userService.setUserForbidden(userID,value);
        return MsgUtil.makeMsg(MsgCode.CHANGE_LOGIN_STATUS);
    }
    @RequestMapping("/register")
    public Msg register(@RequestBody JSONObject user)
    {
        System.out.println(user);
        String nickname = user.getString("nickname");
        String username = user.getString("username");
        String password = user.getString("password");
        String email = user.getString("email");
        String phone = user.getString("phone");
        String description = user.getString("intro");
        int gender = user.getInt("gender");
        try {
            userService.register(nickname,username,password,email,phone,description,gender);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,"注册成功");
        }catch (Exception e)
        {
            System.out.println("注册失败");
            return MsgUtil.makeMsg(MsgUtil.ERROR,"注册失败");
        }

    }

}
