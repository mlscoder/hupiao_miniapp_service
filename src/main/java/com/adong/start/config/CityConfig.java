package com.adong.start.config;

import java.util.HashMap;
import java.util.Map;

public class CityConfig {
    public static Map<String, String> cityMap = new HashMap<String, String>() {{
        put("sh", "上海");
        put("bj", "北京");
        put("gz", "广州");
        put("sz", "深圳");
        put("hz", "杭州");
        put("nj", "南京");
        put("wh", "武汉");
        put("cd", "成都");
        put("cq", "重庆");
    }};
}
