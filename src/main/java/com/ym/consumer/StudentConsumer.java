package com.ym.consumer;

import com.ym.dto.StudentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class StudentConsumer {

    @RabbitListener(queues = {"${queue.students.name}"})
    public void listenToStudentsQueue(@Payload StudentDTO studentDTO){
        System.out.println(studentDTO);
    }
}
