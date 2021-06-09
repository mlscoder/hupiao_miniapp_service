package com.adong.start.task;


import com.adong.start.model.*;
import com.adong.start.service.*;
import com.adong.start.util.SendUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时推送服务
 * 早10点到晚上10点
 * 每15分钟运行一次
 */
@Component
public class PushTask {

    @Autowired
    WeixinUserInfoSerivice userInfoSerivice;

    @Autowired
    CustomServcie customServcie;

    @Autowired
    CustomHistoryService customHistoryService;

    @Autowired
    RentinfoService rentinfoService;

    @Autowired
    HouseinfoService houseinfoService;
    @Autowired
    ImageService imageService;


    int count = 2;

    // 定时器逻辑。早上8点-晚上22点，每15分钟推送一次
    @Scheduled(cron = "0 0/30 6-23 * * ?")
    public void push() {
        List<Custom> customList = customServcie.list();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.add(Calendar.DAY_OF_MONTH, -30);

        for (Custom c : customList) {
            QueryWrapper<Rentinfo> q1 = new QueryWrapper<>();

            if (null != c.getCity()) {
                q1.eq("city", c.getCity());
            }
            if (null != c.getStation()) {
                String[] stations = c.getStation().split(",");
                q1.in("station", stations);
            }
            if (c.getPay() != 0) {
                q1.eq("pay", c.getPay());
            }
            if (null != c.getPrice() && c.getPrice() > 0) {
                q1.le("price", c.getPrice());
            }
            if (null != c.getOnlyGirl() && c.getOnlyGirl() == 1) {
                q1.eq("only_girl", c.getOnlyGirl());
            }
            if (null != c.getRentType() && c.getRentType() != 0) {
                String type = c.getRentType() == 1 ? "hz" : "zz";
                q1.eq("rent_type", type);
            }
            q1.ge("identity", "0");
            //仅仅查询最近一天的内的数据，做到实时推送
            q1.ge("create_date", calendar.getTime());
            List<Rentinfo> rentinfos = rentinfoService.list(q1);
            if (rentinfos.size() > 0) {
                Iterator<Rentinfo> iterator = rentinfos.iterator();
                while (iterator.hasNext()) {
                    Houseinfo houseinfo = houseinfoService.getById(iterator.next().getHId());
                    String creator = houseinfo.getCreator();
                    QueryWrapper<Houseinfo> q = new QueryWrapper<>();
                    q.eq("creator", creator);
                    q.ge("createDate", calendar2.getTime());
                    if (houseinfoService.count(q) > count) {
                        iterator.remove();
                    }
                }
            }
            if (rentinfos.size() == 0) {
                continue;
            }
            //查询已经推送的消息，确定没有推送的
            QueryWrapper<CustomHistory> pushqueryWrapper = new QueryWrapper<>();
            pushqueryWrapper.eq("userId", c.getUserId());
            pushqueryWrapper.ge("createDate", calendar.getTime());

            //当天推送历史
            List<CustomHistory> ispushlist = customHistoryService.list(pushqueryWrapper);
            Set<Integer> set = new HashSet<>();
            //统计当天微信推送的次数
            int pushcount = 0;
            for (CustomHistory pushdone : ispushlist) {
                set.add(pushdone.getRid());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String today = df.format(new Date());
                boolean flag = false;
                try {
                    Date day = df.parse(today);
                    if (day.getTime() < pushdone.getCreateDate().getTime()) {
                        flag = true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (pushdone.getIspush() == 1 && flag) {
                    pushcount++;
                }
            }


            if (rentinfos.size() > 0) {
                List<CustomHistory> histories = new ArrayList<>();
                for (Rentinfo r : rentinfos) {
                    if (!set.contains(r.getId())) {
                        Houseinfo houseinfo = houseinfoService.getById(r.getHId());
                        CustomHistory customHistory = new CustomHistory();
                        customHistory.setRid(r.getId());
                        customHistory.setUserId(c.getUserId());
                        customHistory.setTitle(houseinfo.getTitle());
                        customHistory.setUrl(r.getUrl());
                        customHistory.setCreateDate(new Date());
                        customHistory.setIspush(0);
                        histories.add(customHistory);
                    }
                }
                //推送没有达到三次，且当前有中标数据
                if (pushcount < 3 && histories.size() > 0) {
                    CustomHistory push = histories.get(0);
                    WeixinUserInfo user = new WeixinUserInfo();
                    user.setUserId(push.getUserId());
                    System.out.println(push.getUserId() + "---" + pushcount);
                    user = userInfoSerivice.findByUser(user);
                    if (SendUtil.sendmessage(user.getOpenId(), push.getTitle(), push.getRid(), pushcount + 1)) {
                        push.setIspush(1);
                    }
                }
                customHistoryService.saveBatch(histories);
            }
        }
    }


}
