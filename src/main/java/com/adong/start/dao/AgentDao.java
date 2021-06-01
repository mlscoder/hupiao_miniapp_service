package com.adong.start.dao;

import com.adong.start.model.Agent;
import com.adong.start.model.Houseinfo;
import com.adong.start.model.StationAgent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentDao extends BaseMapper<Agent> {


    /*查询出发帖数符合要求的数据*/
    List<Agent> selectAgent();

    /*获取根据豆瓣id获取中介信息*/
    Agent getAgent(@Param("creator") String creator);

    /*根据创建者统计出地铁站信息*/
    List<StationAgent> getStation(@Param("creator") String creator);

    /*获取创建者最近更新的帖子*/
    List<Houseinfo> getLastInfo(@Param("creator") String creator);

    /*删除地铁和中介的关系*/
    void deleteStation(@Param("creator") String creator);

    /*新增地铁和中介的关系*/
    void insertStation(List<StationAgent> stationAgents);
}
