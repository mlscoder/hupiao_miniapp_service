package com.adong.start.util;

import com.adong.start.config.WxConfig;

public class UserInfoUtil {

    // 获取code的请求地址
    public static String Get_Code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STAT#wechat_redirect";

    // 替换字符串
    public static String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
        return String.format(Get_Code, APPID, REDIRECT_URI, SCOPE);
    }

    // 获取Web_access_tokenhttps的请求地址
    public static String Web_access_tokenhttps = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


    // 替换字符串
    public static String getWebAccess(String CODE) {
        return String.format(Web_access_tokenhttps, WxConfig.appid, WxConfig.appkey, CODE);
    }

    public static String access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    // 替换字符串
    public static String getAccessToken() {
        return String.format(access_token, WxConfig.appid, WxConfig.appkey);
    }

    //  发送订阅消息
    public static String sendmessage = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";


    public static String getSendMessage(String token) {
        return String.format(sendmessage, token);
    }

}
