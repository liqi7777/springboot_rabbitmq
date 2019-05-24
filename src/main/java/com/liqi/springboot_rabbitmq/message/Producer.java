package com.liqi.springboot_rabbitmq.message;

import com.liqi.springboot_rabbitmq.config.direct.DirectKeyInterface;
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

    public void send(String message) {
        rabbitTemplate.convertAndSend(DirectKeyInterface.DIRECT_EXCHANGE_NAME, DirectKeyInterface.DIRECT_KEY, message);
        log.info("消息发送成功：{}", message);
    }
}
