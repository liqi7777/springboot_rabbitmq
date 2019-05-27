package com.liqi.springboot_rabbitmq.service.serviceImp;
import com.liqi.springboot_rabbitmq.entity.User;
import com.liqi.springboot_rabbitmq.message.Producer;
import com.liqi.springboot_rabbitmq.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    DOTO
 * @Author:         Kevin
 * @CreateDate:     2019/4/16 22:06
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/4/16 22:06
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Transactional
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private Producer producer;

    /**
     * 增加user，发送要增加的user信息到消息队列（RabbitMQ）
     * 从而起到流量削峰的作用
     * @param user
     */
    @Override
    public void addUser(User user) {
        producer.sendObject(user);
    }

    /**
     * 删除user
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        producer.send(String.valueOf(id));
    }
}
