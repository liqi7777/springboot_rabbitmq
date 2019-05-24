package com.liqi.springboot_rabbitmq.message;

import com.liqi.springboot_rabbitmq.config.direct.DirectKeyInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class Consumer {

    @RabbitHandler
    @RabbitListener(queues = {DirectKeyInterface.DIRECT_QUEUE_NAME}) //监听指定队列
    public void recevice(String message) {
        log.info("接收到消息：{}", message);
    }
}
