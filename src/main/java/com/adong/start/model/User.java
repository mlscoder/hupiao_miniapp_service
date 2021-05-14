package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘栋
 * @ClassName: User
 * @Description: TODO 用户
 * @date 2019年3月7日 下午6:36:45
 */
@Data
public class User {
    /**
     * 串行版本ID
     */
    private static final long serialVersionUID = -5748361367431356713L;
    /**
     * 用户id
     */
    @TableId(value = "userId")
    private Integer userId;

    /**
     * 用户名称
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 注册时间
     */
    @TableField(value = "createDate")
    private Date createDate;

    /**
     * 备注
     */
    private String note;

    /**
     * 用户级别
     */
    private Integer level;

    /**
     * 密码
     */
    private String password;

    private Integer status;


}
