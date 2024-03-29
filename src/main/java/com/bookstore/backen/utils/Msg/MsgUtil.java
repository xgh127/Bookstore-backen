package com.bookstore.backen.utils.Msg;

import net.sf.json.JSONObject;

public class MsgUtil {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int LOGIN_USER_ERROR = -100;
    public static final int NOT_LOGGED_IN_ERROR = -101;
    public static final String SUCCESS_MSG = "成功！";
    public static final String ERROR_MSG = "错误！";
    public static final String CHANGE_FORBIDEN_SUCCESS = "修改登陆状态成功！";
    public static final String LOGOUT_ERR_MSG = "退出登录异常！";
    public static final String LOGIN_USER_ERROR_MSG = "用户名或密码错误，请重新输入！";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "登录失效，请重新登录！";
    public static final String REGISTER_SUCCESS = "注册成功！";
    public static final String REGISTER_ERROR = "注册失败！出错啦！";

    public static final String ADD_TO_SHOPCART_SUCCESS = "添加到购物车成功！";

    public static final String EDIT_SHOPCART_FAIL = "编辑购物车信息失败";

    public static final String QUERY_BookID_NOT_EXIST = "本书不存在，请选择其他图书";

    public static final String USERNAME_REPEATED = "用户名重复，请重新输入用户名";

    public static final String SET_LOGIN_PERMISSION_SUCCESS = "设置用户登录许可成功！";
    public static final String SET_LOGIN_PERMISSION_FAIL = "设置用户登录许可失败！";

    public static Msg makeMsg(MsgCode code,JSONObject data){
        return new Msg(code, data);
    }

    public static Msg makeMsg(MsgCode code, String msg, JSONObject data){
        return new Msg(code, msg, data);
    }

    public static Msg makeMsg(MsgCode code){
        return new Msg(code);
    }

    public static Msg makeMsg(MsgCode code, String msg){
        return new Msg(code, msg);
    }

    public static Msg makeMsg(int status, String msg, JSONObject data){
        return new Msg(status, msg, data);
    }

    public static Msg makeMsg(int status, String msg){
        return new Msg(status, msg);
    }
}