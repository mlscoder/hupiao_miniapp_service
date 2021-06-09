package com.adong.start.util;

import com.adong.start.config.WxConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 订阅消息推送工具
 */
public class SendUtil {


    public static boolean sendmessage(String openId, String title, Integer rid, Integer count) {
        String response = HttpsUtil.httpsRequestToString(UserInfoUtil.getAccessToken(), "GET", null);
        String access_token = (String) JSON.parseObject(response).get("access_token");
        //请求订阅消息的url
        UserInfoUtil.getSendMessage(access_token);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", openId);
        jsonObject.put("template_id", WxConfig.mubanid);
        jsonObject.put("page", "pages/confirm/confirm?rid=" + rid + "&count=" + count);

        JSONObject data = new JSONObject();
        JSONObject thing1 = new JSONObject();
        if (title.length() > 20) {
            title = title.substring(0, 18) + "..";
        }
        thing1.put("value", title);
        JSONObject date3 = new JSONObject();
        date3.put("value", simpleDateFormat.format(new Date()));
        data.put("thing1", thing1);
        data.put("time8", date3);
        jsonObject.put("data", data);

        System.out.println(jsonObject.toJSONString());

        String
                sendresponse = HttpsUtil.httpsRequestToString(UserInfoUtil.getSendMessage(access_token), "POST", jsonObject.toJSONString());


        JSONObject sendresponse2json = JSON.parseObject(sendresponse);

        System.out.println(sendresponse2json);

        if ((Integer) sendresponse2json.get("errcode") == 0) {
            return true;
        } else {
            return false;
        }
    }


}
