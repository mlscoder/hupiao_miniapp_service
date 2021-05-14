package com.adong.start.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 定制信息实体类
 */

@TableName("Custom")
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
     * 地址
     */
    private String address;

    /**
     * 地铁线路
     */
    private Integer line;

    /**
     * 地铁站名称
     */
    private String station;

    /**
     * 合/整租标记、1合2整租
     */
    private Integer renttype;

    /**
     * 付款类型
     */
    private Integer pay;

    /**
     * 是否仅限女生标注
     */
    private Integer onlygril;

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

    /*状态，默认为1，0位取消定制*/
    private Integer status = 1;


    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getRenttype() {
        return renttype;
    }

    public void setRenttype(Integer renttype) {
        this.renttype = renttype;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getOnlygril() {
        return onlygril;
    }

    public void setOnlygril(Integer onlygril) {
        this.onlygril = onlygril;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
