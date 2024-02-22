package com.ym.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    @RabbitListener(queues = {"${queue.emails.name}"})
    public void listenToEmailsQueue(@Payload String email){
        System.out.println(email);
    }
}
