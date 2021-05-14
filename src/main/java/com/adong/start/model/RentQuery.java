package com.adong.start.model;


import lombok.Data;

@Data
public class RentQuery {
    /*发布次数*/
    private Integer logicCount;
    /*支付方式*/
    private Integer logicPay;
    /*出租方式*/
    private Integer logicSort;
    /*仅限女生*/
    private Integer logicGirl;
    /*价格上限*/
    private Integer money_max;
    /*价格下限*/
    private Integer money_min;
    /*页码*/
    private Integer pageno;
    /*每页条数*/
    private Integer limit = 10;
    /*线路id*/
    private String line_id;
    /*站点id*/
    private String stand_id;

}
