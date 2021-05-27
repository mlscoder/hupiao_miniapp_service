package com.adong.start.service.impl;

import com.adong.start.dao.CityDao;
import com.adong.start.model.City;
import com.adong.start.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityDao, City> implements CityService {
}
