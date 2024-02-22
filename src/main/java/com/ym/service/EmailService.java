package com.ym.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${email.from}")
    private String from;
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    private String getNewLine(){
        return System.getProperty("line.separator");
    }

    public String buildMailBody(String firstName, String lastName){
        return
                "Hello Mr " + lastName + " " + firstName + ","
                + getNewLine() +
                "We want to confirm your registration with us, and we will contact you later to talk about our program."
                + getNewLine() +
                "Thank you for joining us, and see you later"
                + getNewLine() +
                "Bye Bye";
    }

    public void sendSimpleMessage(String to,String subject ,String mailBody){
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(mailBody);

            javaMailSender.send(simpleMailMessage);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
