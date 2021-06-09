package com.adong.start.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定制信息实体类
 */

@TableName("Custom")
@Data
public class Custom implements Serializable {
    /**
     * 串行版本ID
     */
    private static final long serialVersionUID = -5748361367431356717L;
    /**
     * 信息id，自增主键 需要配置type = IdType.AUTO  ！！！！！
     */
    @TableId(value = "customId", type = IdType.AUTO)
    private Integer customId;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private String userId;

    /**
     * 价格
     */
    private Integer price;


    /**
     * 城市
     */
    private String city;

    /**
     * 地铁站名称
     */
    private String station;

    /**
     * 合/整租标记、1合2整租
     */
    private Integer rentType;

    /**
     * 付款类型
     */
    private Integer pay;

    /**
     * 是否仅限女生标注
     */
    private Integer onlyGirl;

    /**
     * 创建者发布的次数
     */
    private Integer count;

    /*创建时间*/
    @TableField(value = "createDate")
    private Date createDate;


    /*创建时间*/
    @TableField(value = "updateDate")
    private Date updateDate;

}
