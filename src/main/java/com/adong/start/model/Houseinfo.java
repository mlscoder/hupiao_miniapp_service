package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "house_info")
public class Houseinfo {

    private Integer id;

    private String title;

    private String text;

}
