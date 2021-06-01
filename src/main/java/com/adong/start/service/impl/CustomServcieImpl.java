package com.adong.start.service.impl;

import com.adong.start.dao.CustomDao;
import com.adong.start.model.Custom;
import com.adong.start.service.CustomServcie;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomServcieImpl extends ServiceImpl<CustomDao, Custom>
        implements CustomServcie {

    @Override
    public boolean mySaveOrUpdate(Custom custom) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId", custom.getUserId());
        Custom custom1 = getOne(queryWrapper);
        if (null == custom1) {
            return save(custom);
        } else {
            removeById(custom1.getCustomId());
            return save(custom);
        }

    }
}
