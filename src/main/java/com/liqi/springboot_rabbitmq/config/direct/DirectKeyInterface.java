package com.liqi.springboot_rabbitmq.config.direct;


public interface DirectKeyInterface {

    //交换机名
    String DIRECT_EXCHANGE_NAME = "direct_exchange";

    //持久化交换机
    String DIRECT_DURABLE_EXCHANGE_NAME = "direct_durable_exchange";

    //持久化队列
    String DIRECT_DURABLE_QUEUE_NAME = "direct_durable_queue";

    //队列名
    String DIRECT_QUEUE_NAME = "direct_queue";

    //路由键
    String DIRECT_KEY = "direct_key";


}
