package com.adong.start.service.impl;

import com.adong.start.dao.SubwayDao;
import com.adong.start.model.Subway;
import com.adong.start.service.SubwayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SubwayServiceImpl extends ServiceImpl<SubwayDao, Subway> implements SubwayService {
}

