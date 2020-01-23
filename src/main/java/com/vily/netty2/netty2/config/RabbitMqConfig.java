package com.vily.netty2.netty2.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-06-05
 *  
 **/

@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}
