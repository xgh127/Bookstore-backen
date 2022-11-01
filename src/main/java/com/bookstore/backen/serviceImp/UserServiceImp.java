package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.constant.constant;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.service.UserService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import com.bookstore.backen.utils.TimeUtl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Service
@SessionScope
//@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

   private Timestamp startTime;

    @Override
    public Msg checkUser(String username, String password) {

        User user = userDao.checkUser(username,password);
        if (user != null)
        {
            if (user.getForbiddenStatus() == 1)
            {
                return MsgUtil.makeMsg(MsgUtil.ERROR,"用户已经被禁止!");
            }else
            {
                startTime = TimeUtl.getNow();
                JSONObject obj = new JSONObject();
                            obj.put(constant.USERNAME,user.getUsername());
                            obj.put(constant.PRIVILEGE,user.getIdentity());
                            obj.put(constant.USERID,user.getId());
                            //obj.put(constant.LOGIN_TIME,TimeUtl.getNow());
                SessionUtil.setSession(obj);
                System.out.println(SessionUtil.getSession());
                System.out.println(username+"的开始时间为"+startTime);
                JSONObject data =JSONObject.fromObject(user);
                data.remove(constant.PASSWORD);
                return MsgUtil.makeMsg(MsgCode.SUCCESS,"登陆成功",data);
            }
        }
        return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
    }

    @Override
    public String logout() {
        HttpSession session = SessionUtil.getSession();

        String username = (String) session.getAttribute(constant.USERNAME);
        Timestamp now = TimeUtl.getNow();
        System.out.println(username+"所在的session在service层输出为： ====="+session);
        String deltaTime =  TimeUtl.longToDate(now.getTime()-startTime.getTime());
        System.out.println(username+"在线时长"+deltaTime);
        System.out.println(username+"从"+startTime+"到"+now);
        return deltaTime;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void setUserForbidden(Integer userID, Integer status) {
            userDao.setUserForbidden(userID,status);
    }

    @Override
    public User register(String nickname, String username, String password, String email, String phone, String description, int gender) {
        User user = new User();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(password);
        user.setMail(email);
        user.setTel(phone);
        user.setDescription(description);
        user.setGender(gender);
        user.setForbiddenStatus(0);
        return userDao.saveOneUser(user);
    }
}
