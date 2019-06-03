package com.liqi.springboot_rabbitmq.message;

import com.alibaba.fastjson.JSONObject;
import com.liqi.springboot_rabbitmq.config.direct.DirectKeyInterface;
import com.liqi.springboot_rabbitmq.config.fanout.FanoutKeyInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2019/05/24
 * email sky.li@ixiaoshuidi.com
 **/
@Component
@Slf4j
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息发送（direct模式）
     *
     * @param message
     */
    public void send(String message) {
        rabbitTemplate.convertAndSend(DirectKeyInterface.DIRECT_EXCHANGE_NAME, DirectKeyInterface.DIRECT_KEY, message);
        log.info("消息发送成功：{}", message);
    }

    /**
     * 消息发送（direct模式）持久化
     *
     * @param message
     */
    public void sendDurable(String message) {
        rabbitTemplate.convertAndSend(DirectKeyInterface.DIRECT_DURABLE_EXCHANGE_NAME, DirectKeyInterface.DIRECT_KEY, message);
    }

    /**
     * 发送对象
     *
     * @param object
     */
    public void sendObject(Object object) {
        String json = JSONObject.toJSONString(object);
        rabbitTemplate.convertAndSend(DirectKeyInterface.DIRECT_EXCHANGE_NAME, DirectKeyInterface.DIRECT_KEY, json);
    }

    /**
     * 消息发送（广播订阅）
     *
     * @param message
     */
    public void sendOfFanout(String message) {
        rabbitTemplate.convertAndSend(FanoutKeyInterface.FANOUT_EXCHANGE_NAME, FanoutKeyInterface.FANOUT_KEY, message);
        log.info("消息发送成功：{}", message);
    }


}
