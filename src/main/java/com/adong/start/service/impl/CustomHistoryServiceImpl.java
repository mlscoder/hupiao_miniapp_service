package com.adong.start.service.impl;

import com.adong.start.dao.CustomHistoryDao;
import com.adong.start.model.CustomHistory;
import com.adong.start.service.CustomHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomHistoryServiceImpl extends ServiceImpl<CustomHistoryDao, CustomHistory>
        implements CustomHistoryService {
}

