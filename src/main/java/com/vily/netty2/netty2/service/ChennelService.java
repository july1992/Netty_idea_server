package com.vily.netty2.netty2.service;

import com.vily.netty2.netty2.bean.ChennelMsg;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-06-05
 *  
 **/

@Service
public class ChennelService {

    @Autowired
    RabbitTemplate rabbitTemplate;



    // 发送
    public void test() {
        rabbitTemplate.convertAndSend("vily.direct", "hluffy", "xxxx");

    }


    @RabbitListener(queues = "hluffy")
    public void receive(String data){


        System.out.println("消息："+data);
    }
//    // 可以在这里接收，也可以在service里接收
//    public void getMsg() {
//
//        Object hluffy = rabbitTemplate.receiveAndConvert("hluffy");
//
//        System.out.println("--------------------------------");
//        System.out.println(hluffy);
////        System.out.println(hluffy.getClass());
//
//    }


}
