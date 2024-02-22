package com.ym.producer;

import com.ym.dto.StudentDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegistrationProducer {

    @Value("${exchange.registrations.name}")
    private String registrationsExchangeName;
    @Value("${routing-key.registrations.name}")
    private String registrationsKeyName;

    private final RabbitTemplate rabbitTemplate;

    public RegistrationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStudentForRegistration(StudentDTO studentDTO){
        rabbitTemplate.convertAndSend(registrationsExchangeName, registrationsKeyName, studentDTO);
    }
}
