package com.liqi.springboot_rabbitmq.message;

import com.liqi.springboot_rabbitmq.config.delay.DelayKeyInterface;
import com.liqi.springboot_rabbitmq.config.direct.DirectKeyInterface;
import com.liqi.springboot_rabbitmq.config.fanout.FanoutKeyInterface;
import com.liqi.springboot_rabbitmq.config.topic.TopicKeyInterface;
import com.liqi.springboot_rabbitmq.mapper.RegisterDao;
import com.liqi.springboot_rabbitmq.utils.AckUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Sky
 * create 2019/05/24
 * email sky.li@ixiaoshuidi.com
 **/
@Component
@Slf4j
public class Consumer {

    @Autowired
    private RegisterDao registerDao;

    //    @RabbitHandler
//    @RabbitListener(queues = {DirectKeyInterface.DIRECT_QUEUE_NAME, DirectKeyInterface.DIRECT_DURABLE_QUEUE_NAME})
    //监听指定队列
    public void recevice(String message) {
        log.info("接收到消息：{}", message);
    }

    /**
     * 接收删除对象信息
     *
     * @param json
     */
    @RabbitHandler
    @RabbitListener(queues = DirectKeyInterface.DIRECT_QUEUE_NAME)
    public void receiveObjectDel(Channel channel, String json, Message message, @Headers Map<String, Object> map) {

        log.info("接收到的id：" + json);
        // int id = Integer.parseInt(json);
//        registerDao.deleteUser(id);
        //<P>代码为在消费者中开启消息接收确认的手动ack</p>
        //<H>配置完成</H>
        //<P>可以开启全局配置</p>
        AckUtils.ack(channel, message, map);
    }

    /**
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = FanoutKeyInterface.FANOUT_QUEUE_A_NAME)
    public void receiveFanoutA(String content, Channel channel, Message message) throws IOException {
        log.info("fanoutA-content:{}", content);
        log.info("fanoutA-message:{}", new String(message.getBody(), "utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitHandler
    @RabbitListener(queues = FanoutKeyInterface.FANOUT_QUEUE_B_NAME)
    public void receiveFanoutB(String content, Channel channel, Message message) throws IOException {
        log.info("fanoutB-content:{}", content);
        log.info("fanoutB-message:{}", new String(message.getBody(), "utf-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitHandler
    @RabbitListener(queues = TopicKeyInterface.TOPIC_QUEUE_NAME_A)
    public void receiveTopicA(String content, Channel channel, Message message) throws IOException {
        log.info("topicA-content:{}", content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    @RabbitHandler
    @RabbitListener(queues = DelayKeyInterface.DELAYMSG_RECEIVE_QUEUE_NAME)
    public void receiveDelayMsg(Channel channel, String json, Message message, @Headers Map<String, Object> map) {

        log.info("接收到的消息" + json);
        log.info("接收时间：" + LocalDateTime.now());
        //<P>代码为在消费者中开启消息接收确认的手动ack</p>
        //<H>配置完成</H>
        //<P>可以开启全局配置</p>
        AckUtils.ack(channel, message, map);
    }


    @RabbitHandler
    @RabbitListener(queues = DelayKeyInterface.DELAY_QUEUE_NAME_PLUGIN)
    public void receiveDelayMsgPlugin(Channel channel, String json, Message message, @Headers Map<String, Object> map) {

        log.info("接收到的消息" + json);
        log.info("接收时间：" + LocalDateTime.now());
        //<P>代码为在消费者中开启消息接收确认的手动ack</p>
        //<H>配置完成</H>
        //<P>可以开启全局配置</p>
        AckUtils.ack(channel, message, map);
    }


}
