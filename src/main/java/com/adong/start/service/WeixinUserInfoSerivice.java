package com.adong.start.service;

import com.adong.start.model.WeixinUserInfo;

public interface WeixinUserInfoSerivice {

	WeixinUserInfo findByUser(WeixinUserInfo userInfo);

	void insertUser(WeixinUserInfo userInfo);
	
	void updateUser(WeixinUserInfo userInfo);
}
