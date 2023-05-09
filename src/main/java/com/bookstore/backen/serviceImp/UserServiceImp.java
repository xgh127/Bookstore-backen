package com.bookstore.backen.serviceImp;

import com.bookstore.backen.Dao.UserAuthDao;
import com.bookstore.backen.Dao.UserDao;
import com.bookstore.backen.constant.constant;
import com.bookstore.backen.entity.User;
import com.bookstore.backen.entity.UserAuth;
import com.bookstore.backen.repository.UserAuthRepository;
import com.bookstore.backen.service.UserService;
import com.bookstore.backen.utils.Msg.Msg;
import com.bookstore.backen.utils.Msg.MsgCode;
import com.bookstore.backen.utils.Msg.MsgUtil;
import com.bookstore.backen.utils.Session.SessionUtil;
import com.bookstore.backen.utils.TimeUtl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
@SessionScope
//@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthDao userAuthDao;

   private Timestamp startTime;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Msg checkUser(String username, String password) {

        User user = userDao.getUserByUsername(username);
        System.out.println(user.getUserAuth().getUsername());
        UserAuth userAuth = user.getUserAuth();
        if (Objects.equals(password, userAuth.getPassword()))
        {
            if (userAuth.getForbiddenStatus() == 1)
            {
                return MsgUtil.makeMsg(MsgUtil.ERROR,"“您的账号已经被禁用!");
            }else
            {
                startTime = TimeUtl.getNow();
                JSONObject obj = new JSONObject();
                            obj.put(constant.USERNAME,user.getUsername());
                            obj.put(constant.PRIVILEGE,userAuth.getUserType());
                            obj.put(constant.USERID,user.getId());
                SessionUtil.setSession(obj);
                System.out.println(SessionUtil.getSession());
                System.out.println(username+"的开始时间为"+startTime);

                JSONObject obj1 = new JSONObject();
                obj1.put(constant.NICKNAME,user.getNickname());
                obj1.put(constant.USERNAME,user.getUsername());
                obj1.put(constant.TEL,user.getTel());
                obj1.put(constant.MAIL,user.getMail());
                obj1.put(constant.DESCRIPTION,user.getDescription());
                obj1.put(constant.USERTYPE,userAuth.getUserType());
                obj1.put(constant.FORBIDDENSTATUS,userAuth.getForbiddenStatus());
                return MsgUtil.makeMsg(MsgCode.SUCCESS,"登陆成功", obj1);
            }
        }
        return MsgUtil.makeMsg(MsgCode.ERROR,"登陆失败！请检查你的用户名或密码！");
    }

    /**
     * 修改个人信息
     * @param username
     * @param nickname
     * @param email
     * @param tel
     * @param description
     * @return
     */

    @Override
    public User modifyUserInfo(String username,String nickname, String email, String tel, String description) {

        User user = userDao.findUserByUserName(username);
        if (user != null)
        {
            user.setNickname(nickname);
            user.setMail(email);
            user.setTel(tel);
            user.setDescription(description);
            return userDao.saveOneUser(user);
        }
        return null;
    }

    /**
     * 登出
     * @return
     */
    @Override
    public String logout() {
        HttpSession session = SessionUtil.getSession();

        String username = (String) session.getAttribute("username");
        Timestamp now = TimeUtl.getNow();
        System.out.println(username+"所在的session在service层输出为： ====="+session);
        String deltaTime =  TimeUtl.longToDate(now.getTime()-startTime.getTime());
        System.out.println(username+"在线时长"+deltaTime);
        System.out.println(username+"从"+startTime+"到"+now);
        return deltaTime;
    }

    /**
     * 管理员获取所有的用户信息
     * @return
     */
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    /**
     * 设置用户的禁用状态
     * @param userID
     * @param status
     */
    @Override
    public void setUserForbidden(Integer userID, Integer status) {
            userDao.setUserForbidden(userID,status);
    }

    /**
     * 注册
     * @param nickname
     * @param username
     * @param password
     * @param email
     * @param phone
     * @param description
     * @param gender
     * @return
     */
    @Override
    @Transactional
    public int register(String nickname, String username, String password, String email, String phone, String description, int gender) {
        User checkUser = userDao.getUserByUsername(username);
        if(checkUser == null) {//检查用户名是否重复
            User user = new User();
            user.setNickname(nickname);
            user.setUsername(username);
            user.setMail(email);
            user.setTel(phone);
            user.setDescription(description);
            user.setGender(gender);

            UserAuth userAuth = new UserAuth();
            userAuth.setUsername(username);
            userAuth.setPassword(password);
            userAuth.setUserType(1);
            userAuth.setForbiddenStatus(0);
            Integer id = userAuthDao.saveOneUserAuth(userAuth).getId(); // 保存 UserAuth 实体
            user.setAuthId(id);
            user.setUserAuth(userAuth);
            userDao.saveOneUser(user); // 保存 User 实体
            return 0; // 如果没有重复，返回 0

        }else {
            System.out.println("用户名重复");
            return -1;//如果重复了，返回-1
        }
    }
}
