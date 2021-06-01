package com.adong.start.controller;

import com.adong.start.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AgentService agentService;

    @PostMapping("/test")
    @ResponseBody
    public void getUser(HttpServletRequest request) throws IOException {
        agentService.updateAgent();
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
