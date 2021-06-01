package com.adong.start.model;

import lombok.Data;

/**
 * 地铁站和中介关联关系表
 */
@Data
public class StationAgent {
    //城市
    private String city;
    //车站
    private String station;
    //中介id
    private String agent;
}
