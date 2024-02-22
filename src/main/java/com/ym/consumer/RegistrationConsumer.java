package com.ym.consumer;

import com.ym.dto.StudentDTO;
import com.ym.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationConsumer {

    private EmailService emailService;

    @RabbitListener(queues = {"${queue.registrations.name}"})
    public void listenToRegistrationQueue(@Payload StudentDTO studentDTO){
        System.out.println("one message");
        emailService.sendSimpleMessage(
                studentDTO.getEmail(),
                "Registration confirmation",
                emailService.buildMailBody(studentDTO.getFirsName(), studentDTO.getLastName())
        );
    }
}
