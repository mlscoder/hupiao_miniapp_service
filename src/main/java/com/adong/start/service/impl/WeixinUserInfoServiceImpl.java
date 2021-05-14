package com.adong.start.service.impl;

import com.adong.start.dao.WeixinUserInfoDao;
import com.adong.start.model.WeixinUserInfo;
import com.adong.start.service.WeixinUserInfoSerivice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeixinUserInfoServiceImpl implements WeixinUserInfoSerivice {


    @Autowired
    WeixinUserInfoDao weixinUserInfo;


    @Override
    public WeixinUserInfo findByUser(WeixinUserInfo userInfo) {

        QueryWrapper queryWrapper = new QueryWrapper();
        if (userInfo.getOpenId() != null) {
            queryWrapper.eq("open_Id", userInfo.getOpenId());
        }
        if (userInfo.getUserId() != null) {
            queryWrapper.eq("user_Id", userInfo.getUserId());
        }
        return weixinUserInfo.selectOne(queryWrapper);
    }

    @Override
    public void insertUser(WeixinUserInfo userInfo) {
        weixinUserInfo.insert(userInfo);
    }

    @Override
    public void updateUser(WeixinUserInfo userInfo) {
        weixinUserInfo.updateById(userInfo);
    }
}
