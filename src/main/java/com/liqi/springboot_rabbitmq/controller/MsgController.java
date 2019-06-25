package com.liqi.springboot_rabbitmq.controller;

import com.liqi.springboot_rabbitmq.config.delay.DelayKeyInterface;
import com.liqi.springboot_rabbitmq.message.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Sky
 * create 2019/05/24
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@Slf4j
public class MsgController {
    @Autowired
    private Producer producer;

    /**
     * 发送消息
     *
     * @return
     */
    @GetMapping("/helloRabbitMQ")
    public String sendMsg() {
//        producer.send("hello world!");
//        producer.sendDurable("hello liqi");
//        producer.sendOfFanout("hello!");
//        producer.sendOfTopic("hello topic!");
        producer.sendOfDelayMsg("hello,delayMsg");
        return "success";
    }


}
