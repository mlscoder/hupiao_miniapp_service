package com.adong.start.service;

import com.adong.start.model.Custom;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomServcie extends IService<Custom> {
    boolean mySaveOrUpdate(Custom custom);
}
