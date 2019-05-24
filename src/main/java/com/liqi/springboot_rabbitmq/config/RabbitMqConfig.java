package com.liqi.springboot_rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Sky
 * create 2019/05/24
 * email sky.li@ixiaoshuidi.com
 **/
@Configuration
@Slf4j
public class RabbitMqConfig {
    @Autowired
    private Environment env;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 定制rabbitMQ模板
     * <p>
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收消息成功回调
     * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中回调
     * </p>
     *
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        //配置连接信息
        rabbitTemplate.setConnectionFactory(connectionFactory());
        //开启消息确认机制
        rabbitTemplate.setMandatory(true);
        //配置消息确认机制
        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallback());
        //配置消息确认机制
        rabbitTemplate.setReturnCallback(new RabbitReturnCallback());
        return rabbitTemplate;
    }

    /**
     * 连接工厂（配置连接信息）
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("spring.rabbitmq.host"),
                env.getProperty("spring.rabbitmq.port", int.class));
        //用户名
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username"));
        //密码
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password"));
        //虚拟主机
        connectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.virtual-host"));
        //消息确认机制 --- 是否回调(默认false）
        connectionFactory.setPublisherConfirms(env.getProperty("spring.rabbitmq.publisher-confirms", Boolean.class));
        //消息确认机制 --- 是否返回回调(默认false）
        connectionFactory.setPublisherReturns(env.getProperty("spring.rabbitmq.publisher-returns", Boolean.class));
        return connectionFactory;
    }

}
