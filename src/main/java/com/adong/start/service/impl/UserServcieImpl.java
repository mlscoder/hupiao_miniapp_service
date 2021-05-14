package com.adong.start.service.impl;

import com.adong.start.dao.UserDao;
import com.adong.start.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServcieImpl implements UserServcie {


    @Autowired
    UserDao userDao;


}
