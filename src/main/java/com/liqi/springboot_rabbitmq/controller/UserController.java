package com.liqi.springboot_rabbitmq.controller;

import com.liqi.springboot_rabbitmq.entity.User;
import com.liqi.springboot_rabbitmq.message.Producer;
import com.liqi.springboot_rabbitmq.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@Slf4j
public class UserController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private Producer producer;


    @GetMapping("/deleteUser")
    public String deleteUser() throws InterruptedException {
        registerService.deleteUser(4);
        return "success";
    }
}
