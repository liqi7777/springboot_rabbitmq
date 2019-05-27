package com.liqi.springboot_rabbitmq.service;


import com.liqi.springboot_rabbitmq.entity.User;

/**
 * @Description: DOTO
 * @Author: Kevin
 * @CreateDate: 2019/4/16 20:59
 * @UpdateUser: Kevin
 * @UpdateDate: 2019/4/16 20:59
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface RegisterService {

    /**
     * 增加user
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 删除user
     *
     * @param id
     */
    void deleteUser(int id);
}
