package com.adong.start.service.impl;

import com.adong.start.dao.RentinfoMapper;
import com.adong.start.model.RentQuery;
import com.adong.start.model.Rentinfo;
import com.adong.start.service.RentinfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentinfoServiceImpl extends ServiceImpl<RentinfoMapper, Rentinfo> implements RentinfoService {

    @Autowired
    RentinfoMapper mapper;

    @Override
    public IPage<Rentinfo> getQueryList(RentQuery rentQuery) {
        Page<Rentinfo> page = new Page<>(rentQuery.getPageno(), 10);
        QueryWrapper<Rentinfo> wrapper = new QueryWrapper<>();

        if (!rentQuery.getStand_id().equals("0")) {
            wrapper.eq("station", rentQuery.getStand_id());
        }

        if (rentQuery.getCity() == null) {
            wrapper.eq("city", "sh");
        } else {
            wrapper.eq("city", rentQuery.getCity());
        }

        if (rentQuery.getLogicPay() != 0) {
            //这里前端写错了 参数传的2
            if (rentQuery.getLogicPay() == 2) {
                wrapper.eq("pay", "3");
            } else {
                wrapper.eq("pay", rentQuery.getLogicPay());
            }
        }

        if (rentQuery.getMoney_max() != 999999) {
            wrapper.le("price", rentQuery.getMoney_max());

        }
        if (rentQuery.getMoney_min() != 0) {
            wrapper.ge("price", rentQuery.getMoney_min());
        }

        if (rentQuery.getLogicGirl() == 1) {
            wrapper.eq("only_girl", "1");
        }
        if (rentQuery.getLogicSort() != 0) {
            if (rentQuery.getLogicSort() == 1) {
                wrapper.eq("rent_type", "hz");
            } else {
                wrapper.eq("rent_type", "zz");
            }

        }
        if (rentQuery.getLogicCount() != 0) {
            wrapper.le("count", rentQuery.getLogicCount());
        }
        wrapper.orderByDesc("id");
        IPage<Rentinfo> result = mapper.selectPage(page, wrapper);
        System.out.println(result.getRecords());
        return result;
    }
}
