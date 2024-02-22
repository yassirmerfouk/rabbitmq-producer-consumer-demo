package com.ym.web;

import com.ym.dto.StudentDTO;
import com.ym.producer.EmailProducer;
import com.ym.producer.RegistrationProducer;
import com.ym.producer.StudentProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/produce")
@AllArgsConstructor
public class ProducerController {

    private EmailProducer emailProducer;
    private StudentProducer studentProducer;
    private RegistrationProducer registrationProducer;

    @GetMapping("/emails/{email}")
    public ResponseEntity<String> sendEmailAddress(@PathVariable String email){
        emailProducer.send(email);
        return ResponseEntity.ok("Email address send with success");
    }

    @PostMapping("/students")
    public ResponseEntity<String> sendStudent(@RequestBody StudentDTO studentDTO){
        studentDTO.setId(UUID.randomUUID().toString());
        studentProducer.sendStudent(studentDTO);
        return ResponseEntity.ok("Student send with success");
    }

    @PostMapping("/students/registration")
    public ResponseEntity<String> sendEmailToStudent(@RequestBody StudentDTO studentDTO){
        studentDTO.setId(UUID.randomUUID().toString());
        registrationProducer.sendStudentForRegistration(studentDTO);
        return ResponseEntity.ok("Success registration, check your inbox for the confirmation");
    }
}
