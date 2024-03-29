package com.bookstore.backen.controllers;

import com.bookstore.backen.constant.constant;
import com.bookstore.backen.service.UserService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@Scope("session")
public class LoginController {
    @Autowired
    UserService userService;
    /*处理前端发来的登录请求，判断是都是已经注册的用户*/
    @RequestMapping(value = "/loginCheck")
    public Msg login(@RequestBody Map<String,String> userInfo)
    {
        String username = userInfo.get(constant.USERNAME);
        String password = userInfo.get(constant.PASSWORD);
        System.out.println(username);
        System.out.println(password);
        return  userService.checkUser(username,password);
    }

    /*登出*/
    @RequestMapping(value = "/logout")
    public Msg logout(){
        String deltaTime = userService.logout();
        Boolean status = SessionUtil.removeSession();

        if(status){
            JSONObject data = new JSONObject();
            data.put("deltaTime",deltaTime);
            System.out.println("在线时长"+deltaTime);
            System.out.println("deltTime"+deltaTime);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,"登出成功",data);
        }
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
    }
}
