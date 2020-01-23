package com.vily.netty2.netty2;

import com.vily.netty2.netty2.netty.NettyServer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit  // 开启基于注解的RabbitMQ 模式
@SpringBootApplication
public class Netty2Application {

    public static void main(String[] args) {
        SpringApplication.run(Netty2Application.class, args);
        NettyServer.getInstance().start();

    }

}
