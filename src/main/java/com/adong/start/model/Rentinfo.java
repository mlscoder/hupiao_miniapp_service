package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 房子信息
 * </p>
 *
 * @author liudong
 * @since 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "rent_info")
public class Rentinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租房信息id
     */
    @TableId
    private Integer id;

    /**
     * 房子id
     */
    private Integer hId;

    /**
     * 猜测价格
     */
    private Integer price;


    /**
     * 地铁站名称
     */
    private String station;

    /**
     * 合/整租标记、1合2整租
     */
    private String rentType;

    /**
     * 付款类型
     */
    private Integer pay;

    /**
     * 是否仅限女生标注
     */
    private Integer onlyGirl;

    /**
     * 推测房东类型
     */
    private Integer identity;

    /**
     * 创建者发布的次数
     */
    private Integer count;

    private Date createDate;
    /**
     * 信息url
     */
    private String url;
    @TableField(exist = false)
    private String showDate;
    // 主图片
    @TableField(exist = false)
    private String mainImgUrl;

    // 所有信息图片
    @TableField(exist = false)
    private List<Image> image_urls;


    @TableField(exist = false)
    private Houseinfo houseinfo;


}
