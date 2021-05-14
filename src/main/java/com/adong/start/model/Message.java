package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("message")
public class Message {

    private String message;

    private String url;


}
