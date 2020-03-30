package com.example.springbootdemofilterinterceptor.interceptors;

/**
 * @ClassName Const
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 14:16
 * @Version 1.0
 */
public class Const {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";
    /************************对象和个体******************************/
    public static final String SESSION_USER = "loginedAgent"; // 用户对象
    public static final String SESSION_LOGINID = "sessionLoginID"; // 登录ID
    public static final String SESSION_USERID = "sessionUserID"; // 当前用户对象ID编号

    public static final String SESSION_USERNAME = "sessionUserName"; // 当前用户对象名称
    public static final Integer PAGE = 10; // 默认分页数
    public static final String SESSION_URL = "sessionUrl"; // 被记录的url
    public static final String SESSION_SECURTY_CODE = "sessionVerifyCode"; // 登录页验证码
    // 时间 缓存时间
    public static final int TIMEOUT = 1800; // s
    public static final String ON_LOGIN = "/logout.html";
    public static final String LOGIN_OUT = "/toLogout";
    // 不验证URL anno: 不验证/authc:受控制的
    public static final String NO_INTERCEPTOR_PATH = ".*((.css)|(.js)|(images)|(login)|(anno)).*";


}
