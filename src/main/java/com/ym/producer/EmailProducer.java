package com.ym.producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    @Value("${exchange.emails.name}")
    private String emailsExchangeName;
    @Value("${routing-key.emails.name}")
    private String emailsKeyName;

    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate amqpTemplate) {
        this.rabbitTemplate = amqpTemplate;
    }

    public void send(String email){
        rabbitTemplate.convertAndSend(emailsExchangeName,emailsKeyName, email);
    }
}
