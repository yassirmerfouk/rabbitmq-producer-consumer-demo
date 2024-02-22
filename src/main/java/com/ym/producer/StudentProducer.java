package com.ym.producer;

import com.ym.dto.StudentDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentProducer {

    @Value("${exchange.students.name}")
    private String studentsExchangeName;
    @Value("${routing-key.students.name}")
    private String studentsKeyName;

    private final RabbitTemplate rabbitTemplate;

    public StudentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStudent(StudentDTO studentDTO){
        rabbitTemplate.convertAndSend(studentsExchangeName, studentsKeyName,studentDTO);
    }
}
