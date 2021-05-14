package com.adong.start.controller;

import com.adong.start.model.UserDO;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @PostMapping("/test")
    @ResponseBody
    public UserDO getUser(HttpServletRequest request) throws IOException {
        
        //读取字节流转成字符串
        String content = charReader(request);
        //json字符串转成java对象
        UserDO user = JSON.parseObject(content, UserDO.class);
        return user;
    }

    //字符串读取
    static String charReader(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        return wholeStr;

    }

}
