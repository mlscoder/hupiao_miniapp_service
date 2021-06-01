package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("agent")
public class Agent {

    //主键id
    @TableId(type = IdType.AUTO)
    private Integer aId;

    //创作者
    private String creator;
    //中介所在城市
    private String city;

    //最近一个月发帖次数
    private Integer monthNum;

    //最近三个月发帖次数
    private Integer quarterNum;

    //最近7天发帖次数
    private Integer weekNum;

    //最近一次发帖时间
    private String createDate;

    // 最近一次更新帖子的id
    private Integer hId;

    // 最近一次更新帖子的标题
    private String title;
}
