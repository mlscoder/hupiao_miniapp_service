package com.adong.start.service.impl;

import com.adong.start.dao.HouseinfoDao;
import com.adong.start.model.Houseinfo;
import com.adong.start.service.HouseinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HouseinfoServiceImpl extends ServiceImpl<HouseinfoDao, Houseinfo>
        implements HouseinfoService {
}
