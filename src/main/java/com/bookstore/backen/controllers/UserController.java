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
        int gender = user.getInt("gender");
        String intro = user.getString("intro");
        String description = null;
        if( intro!= null){
           description =intro;
        }
        ;
        try {
            if(userService.register(nickname,username,password,email,phone,description,gender) >= 0){
                System.out.println("注册成功"+username);
                return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.REGISTER_SUCCESS);
            }else{
                return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.USERNAME_REPEATED);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("注册失败");
            return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.REGISTER_ERROR);
        }

    }
    @RequestMapping("/ModifyUserInfo")
    public User ModifyUserInfo(@RequestBody Map<String,String> userInfo){
        String username = userInfo.get("username");
        String nickname = userInfo.get("nickname");
        String email = userInfo.get("email");
        String phone = userInfo.get("phone");
        String intro = userInfo.get("description");

        String description = null;
        if( intro!= null){
            description =intro;
        }
        User user = userService.modifyUserInfo(username,nickname,email,phone,description);
        return user;
    }

}
