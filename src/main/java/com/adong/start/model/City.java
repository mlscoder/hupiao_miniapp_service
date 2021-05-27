package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "city")
public class City {
    private Integer id;
    private String code;
    private String name;
}
