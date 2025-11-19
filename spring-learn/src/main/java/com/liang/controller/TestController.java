package com.liang.controller;

import com.alibaba.fastjson2.JSON;
import com.liang.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("/data")
    public String getMessage(){
        Message message = new Message();
        message.put("title","测试消息");
        message.put("message","点开查看个人github");
        log.info("获取message");
        return JSON.toJSONString(message);
    }

    @PostMapping("/send")
    public Map<String, Map<String, String>> postMessage(@RequestBody Message message){
        log.info("构建message:{}",message);
        return message.getData();
    }

}
