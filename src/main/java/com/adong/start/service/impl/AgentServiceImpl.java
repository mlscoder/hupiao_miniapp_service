package com.adong.start.service.impl;

import com.adong.start.dao.AgentDao;
import com.adong.start.model.Agent;
import com.adong.start.model.Houseinfo;
import com.adong.start.model.StationAgent;
import com.adong.start.service.AgentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AgentServiceImpl extends ServiceImpl<AgentDao, Agent> implements AgentService {
    @Autowired
    AgentDao agentDao;

    @Override
    public void updateAgent() {
        List<Agent> agents = agentDao.selectAgent();
        for (Agent agent : agents) {
            String creator = agent.getCreator();
            List<StationAgent> stationAgents = agentDao.getStation(creator);
            stationAgents.forEach(a -> {
                a.setAgent(creator);
            });
            Houseinfo info = agentDao.getLastInfo(creator).get(0);
            agent.setHId(info.getId());
            agent.setTitle(info.getTitle());
            agent.setCreateDate(info.getCreateDate().replace("T", " "));

            if (stationAgents.size() > 0) {
                agent.setCity(stationAgents.get(0).getCity());
            }
            if (null != agentDao.getAgent(creator)) {
                agent.setAId(agentDao.getAgent(creator).getAId());

            }
            saveOrUpdate(agent);
            if (stationAgents.size() > 0) {
                agentDao.deleteStation(creator);
                agentDao.insertStation(stationAgents);
            }

        }
    }

    /*前一个月的第一天
     */
    public Date getLastMonthDay1() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        return calendar1.getTime();

    }

    //获取前一个月最后一天
    public Date getLastMonthDay2() {

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        return calendar2.getTime();
    }
}