package com.adong.start.service;

import com.adong.start.model.RentQuery;
import com.adong.start.model.Rentinfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liudong
 * @since 2019-09-30
 */
public interface RentinfoService extends IService<Rentinfo> {

    IPage<Rentinfo> getQueryList(RentQuery rentQuery);
}
