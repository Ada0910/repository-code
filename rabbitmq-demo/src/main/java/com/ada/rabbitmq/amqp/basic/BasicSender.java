package com.ada.rabbitmq.amqp.basic;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.ada.rabbitmq.amqp.basic")
public class BasicSender {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicSender.class);
        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);

        rabbitTemplate.convertAndSend("","Ada_BASIC_FIRST_QUEUE","-------- a direct msg");

        rabbitTemplate.convertAndSend("Ada_BASIC_TOPIC_EXCHANGE","shanghai.ada.teacher","-------- a topic msg : shanghai.ada.teacher");
        rabbitTemplate.convertAndSend("Ada_BASIC_TOPIC_EXCHANGE","changsha.ada.student","-------- a topic msg : changsha.ada.student");

        rabbitTemplate.convertAndSend("Ada_BASIC_FANOUT_EXCHANGE","","-------- a fanout msg");


    }
}
