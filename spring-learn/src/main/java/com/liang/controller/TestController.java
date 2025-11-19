package com.liang.controller;

import com.alibaba.fastjson2.JSON;
import com.liang.bean.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/data")
    public String getMessage(){
        Message message = new Message();
        message.put("title","测试消息");
        message.put("message","点开查看个人github");
        return JSON.toJSONString(message);
    }

}
