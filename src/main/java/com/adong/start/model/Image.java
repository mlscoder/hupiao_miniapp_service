package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Image {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /*对应的rentinfo的 id */
    private Integer rId;
    /*图片url*/
    private String url;

}
