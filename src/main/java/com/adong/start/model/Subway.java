package com.adong.start.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "subways")
public class Subway {
    private Integer id;
    private String subwayLineId;
    private String subwayLineName;
    private String subwayName;
    private String sortIndex;
    private String cityCode;
    private String sortLine;
}
