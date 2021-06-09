package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "house_info")
public class Houseinfo {

    private Integer id;
    private String title;
    private String url;
    private String text;
    @TableField(value = "createDate")
    private String createDate;
    @TableField(value = "crawDate")
    private String crawDate;

    private String creator;
}
