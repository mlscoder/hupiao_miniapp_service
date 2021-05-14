package com.adong.start.dao;

import com.adong.start.model.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends BaseMapper<Message> {
}
