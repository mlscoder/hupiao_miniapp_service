package com.adong.start.task;

import com.adong.start.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时推送服务
 * 早10点到晚上10点
 * 每15分钟运行一次
 */
@Component
public class AgentTask {
    @Autowired
    AgentService agentService;

    @Scheduled(cron = "0 0 10/6 * * ? ")
    public void updateAgent() {
        //更新信息
        agentService.updateAgent();
    }
}
