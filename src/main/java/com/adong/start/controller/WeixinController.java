package com.adong.start.controller;

import com.adong.start.config.Subway;
import com.adong.start.model.*;
import com.adong.start.service.*;
import com.adong.start.util.HttpsUtil;
import com.adong.start.util.UserInfoUtil;
import com.adong.start.util.UtilTool;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/wxapp")
public class WeixinController {
    @Autowired
    WeixinUserInfoSerivice userInfoService;
    @Autowired
    CustomServcie customServcie;
    @Autowired
    CustomHistoryService customHistoryService;
    @Autowired
    RentinfoService rentinfoService;
    @Autowired
    ImageService imageService;
    @Autowired
    HouseinfoService houseinfoService;
    @Autowired
    MessageService messageService;


    String[] subwaylist = Subway.subways.replace(" ", "").split(",|\\s+");


    @Value("${notice}")
    String notice;

    /**
     * 获取用户的openid
     *
     * @param code
     * @return
     */
    @RequestMapping("/getopenId")
    @ResponseBody
    public Map<String, Object> getopenIds(String code) {
        Map<String, Object> map = new HashMap<>();
        byte[] keyByte = null;
        if (code != null || !(code.equals(""))) {
            String CODE = code;
            String WebAccessToken = "";
            String openId = "";
            String userId = null;
            String sessionkey = "";
            // 替换字符串，获得请求URL
            String token = UserInfoUtil.getWebAccess(CODE);// 通过自定义工具类组合出小程序需要的登录凭证

            // 通过https方式请求获得web_access_token并获得小程序的返回
            String response = HttpsUtil.httpsRequestToString(token, "GET", null);
            // 通过JsonObject解析小程序返回数据
            JSONObject jsonObject = JSON.parseObject(response);

            // 如果JasonObject或opeid为空则登录失败
            if (null != jsonObject && jsonObject.getString("openid") != null) {
                try {
                    // 从jsonObject中获取sessionKey的值
                    WebAccessToken = jsonObject.getString("session_key");
                    keyByte = Base64.decodeBase64(WebAccessToken);
                    // 获取openid
                    openId = jsonObject.getString("openid");

                    System.out.println(
                            "》》》获取access_token成功[session_key:" + WebAccessToken + "---------------openid:" + openId);
                    // 在这之后写自己想在登录中进行的各种操作
                    // 如果存在则返回userid

                    WeixinUserInfo one = new WeixinUserInfo();
                    one.setOpenId(openId);
                    one = userInfoService.findByUser(one);
                    String phone = null;
                    if (one == null) {
                        WeixinUserInfo entity = new WeixinUserInfo();
                        entity.setUserId(UtilTool.getUUId());
                        entity.setOpenId(openId);
                        entity.setCreateDate(new Date());
                        userInfoService.insertUser(entity);

                        WeixinUserInfo save = userInfoService.findByUser(entity);
                        userId = save.getUserId();
                        map.put("userId", userId);
                        map.put("hasPhone", false);
                    } else {
                        userId = one.getUserId();
                        map.put("userId", userId);

                    }
                    map.put("status", 1);
                    map.put("msg", "登录成功");

                } catch (JSONException e) {
                    e.printStackTrace();
                    WebAccessToken = null;// 获取code失败
                    System.out.println("获取session_key失败");
                    map.put("stauts", 0);
                    map.put("msg", "登录失败");
                }
            } else {
                System.out.println("获取openid及session_key失败");
                map.put("stauts", 0);
                map.put("msg", "登录失败");
            }
        }
        return map;
    }

    @RequestMapping("/userinfo")
    @ResponseBody
    public void userinfo(String rawData, String userId) {
        JSONObject jsonObject = JSONObject.parseObject(rawData);
        WeixinUserInfo userInfo = JSONObject.toJavaObject(jsonObject, WeixinUserInfo.class);
        userInfo.setStatus(1);
        userInfo.setUserId(userId);
        userInfo.setUpdateDate(new Date());
        userInfoService.updateUser(userInfo);
    }


    @RequestMapping("/sendsms")
    @ResponseBody
    public Map sendsms(String phone, String userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map map = new HashMap();
        int number = new Random().nextInt(999999);
        if (number < 100000) {
            number += 100000;
        }
        // 替换字符串，获得请求URL
        String token = UserInfoUtil.sendsms(phone, number);
        // 通过https方式请求获得
        String response = HttpsUtil.httpsRequestToString(token, "GET", null);
        JSONObject jsonObject = JSON.parseObject(response);
        if (((Integer) jsonObject.get("status")) == 0) {

            map.put("status", 0);
            map.put("msg", "success");
            map.put("number", number);
        } else {
            map.put("status", 1);
            map.put("msg", "fail");
        }
        return map;
    }

    /**
     * 更新手机号码
     *
     * @param userId 用户id
     * @param phone  手机号码
     */
    @RequestMapping("/verify")
    @ResponseBody
    public Map verify(String userId, String phone) {

        Map map = new HashMap();
        try {
            WeixinUserInfo userInfo = new WeixinUserInfo();
            userInfo.setUserId(userId);
            userInfo.setPhone(phone);
            userInfoService.updateUser(userInfo);
            map.put("status", 0);
            map.put("msg", "success");
        } catch (Exception e) {
            map.put("status", 1);
            map.put("msg", "fail");
        }
        return map;
    }


    @RequestMapping("/custom")
    @ResponseBody
    public Map custom(Custom custom) {
        Map map = new HashMap();
        String stations = custom.getStation();

        String[] strings = stations.split(",|，|\\s+");
        StringBuffer sb = new StringBuffer();

        if (strings.length > 3) {
            map.put("status", 0);
            map.put("msg", "最多支持三个地铁站点");
            return map;
        }


        for (int i = 0; i < strings.length; i++) {
            if (!isHave(subwaylist, strings[i])) {
                map.put("status", 0);
                map.put("msg", "站点不符合规范，请查看使用指南");
                return map;
            }
            sb.append(strings[i] + ",");
        }

        String str = sb.toString();
        stations = str.substring(0, str.length() - 1);
        custom.setStation(stations);
        custom.setCreateDate(new Date());
        boolean result = customServcie.mySaveOrUpdate(custom);
        if (result) {
            map.put("status", 1);
            map.put("msg", "添加成功");
        } else {
            map.put("status", 0);
            map.put("msg", "网络错误，稍后重试");
        }
        return map;
    }

    public static boolean isHave(String[] strs, String s) {
        /*此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
         * */
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(s)) {//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
                return true;//查找到了就返回真，不在继续查询
            }
        }
        return false;//没找到返回false
    }


    /**
     * 通过rid获取rentinfo的信息
     * 订阅消息回调
     */
    @RequestMapping("/rentinfo")
    @ResponseBody
    public Map rentinfo(String rid) {
        Map map = new HashMap();
        Rentinfo rentinfo = rentinfoService.getById(rid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        rentinfo.setShowDate(simpleDateFormat.format(rentinfo.getCreateDate()));
        map.put("rentinfo", rentinfo);
        return map;
    }


    /**
     * 根据userid 获取提交的定制信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getcustom")
    @ResponseBody
    public Map getcustom(String userId) {
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId", userId);
        Custom custom = customServcie.getOne(queryWrapper);
        map.put("custom", custom);
        return map;
    }

    /**
     * 根据userid 获取提交的定制信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getcustomhistory")
    @ResponseBody
    public Map getcustomhistory(String userId) {
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId", userId);
        queryWrapper.orderByDesc("createDate");
        queryWrapper.last("limit 10");
        List<CustomHistory> list = customHistoryService.list(queryWrapper);

        List<Integer> ids = list.stream().map(e -> e.getRid()).collect(Collectors.toList());

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.in("id", ids);
        queryWrapper2.orderByDesc("id");
        List<Rentinfo> rentinfoList = rentinfoService.list(queryWrapper2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        rentinfoList.forEach(e -> {
            e.setShowDate(simpleDateFormat.format(e.getCreateDate()));
        });
        map.put("customhistory", rentinfoList);
        return map;
    }


    /**
     * 公告栏
     *
     * @return
     */
    @RequestMapping("/getnotice")
    @ResponseBody
    public Map getnotice() {
        Map map = new HashMap();
        map.put("notice", notice);
        return map;
    }


    /**
     * 查询list
     *
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public Map query(RentQuery rentQuery) {
        Map map = new HashMap();
        IPage<Rentinfo> result = rentinfoService.getQueryList(rentQuery);
        Map house = new HashMap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        result.getRecords().forEach(e -> {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("r_id", e.getId());
            List<Image> list = imageService.list(queryWrapper);
            e.setMainImgUrl(list.size() == 0 ? null : list.get(0).getUrl());
            e.setShowDate(simpleDateFormat.format(e.getCreateDate()));
        });

        house.put("house_list", result.getRecords());
        map.put("data", house);
        map.put("status", 200);
        return map;
    }

    /**
     * 查询一个详情
     *
     * @return
     */
    @RequestMapping("/queryone")
    @ResponseBody
    public Map queryOne(Integer rentid) {
        Map map = new HashMap();
        Map house = new HashMap();
        Rentinfo rentinfo = rentinfoService.getById(rentid);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("r_Id", rentid);
        List<Image> list = imageService.list(queryWrapper);
        rentinfo.setImage_urls(list);

        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("id", rentinfo.getHId());
        Houseinfo houseinfo = houseinfoService.getOne(queryWrapper2);
        rentinfo.setHouseinfo(houseinfo);

        map.put("data", rentinfo);
        map.put("status", 200);
        return map;
    }


    /*获取最新的公告栏信息*/
    @RequestMapping("/message")
    @ResponseBody
    public Map queryMessage() {
        Map map = new HashMap();
        Message message = messageService.list().get(0);
        map.put("data", message);
        return map;
    }


}