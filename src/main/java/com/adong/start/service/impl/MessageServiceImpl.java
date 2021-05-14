package com.adong.start.service.impl;

import com.adong.start.dao.MessageDao;
import com.adong.start.model.Message;
import com.adong.start.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message>
        implements MessageService {
}
