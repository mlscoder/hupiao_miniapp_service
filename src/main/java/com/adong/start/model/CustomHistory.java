package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 我的历史推送
 */
@TableName(value = "customhistory")
public class CustomHistory {


    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer rid;

    @TableField(value = "userId")
    private String userId;

    private String title;

    private String url;

    //创建时间
    @TableField(value = "createDate")
    private Date createDate;

    //    不是数据库表字段
    @TableField(exist = false)
    private String showDate;

    /*微信推送标志位*/
    private Integer ispush;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIspush() {
        return ispush;
    }

    public void setIspush(Integer ispush) {
        this.ispush = ispush;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }
}
