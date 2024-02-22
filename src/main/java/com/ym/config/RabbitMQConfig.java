package com.ym.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queue.emails.name}")
    private String emailsQueueName;
    @Value("${exchange.emails.name}")
    private String emailsExchangeName;
    @Value("${routing-key.emails.name}")
    private String emailsKeyName;

    @Value("${queue.students.name}")
    private String studentsQueueName;
    @Value("${exchange.students.name}")
    private String studentsExchangeName;
    @Value("${routing-key.students.name}")
    private String studentsKeyName;

    @Value("${queue.registrations.name}")
    private String registrationsQueueName;
    @Value("${exchange.registrations.name}")
    private String registrationsExchangeName;
    @Value("${routing-key.registrations.name}")
    private String registrationsKeyName;


    @Bean
    public Queue emailsQueue(){
        return new Queue(emailsQueueName);
    }

    @Bean
    public TopicExchange emailsExchange(){
        return new TopicExchange(emailsExchangeName);
    }

    @Bean
    public Binding emailsBinding(){
        return BindingBuilder
                .bind(emailsQueue())
                .to(emailsExchange())
                .with(emailsKeyName);
    }

    @Bean
    public Queue studentsQueue(){
        return new Queue(studentsQueueName);
    }

    @Bean
    public TopicExchange studentsExchange(){
        return new TopicExchange(studentsExchangeName);
    }

    @Bean
    public Binding studentsBinding(){
        return BindingBuilder
                .bind(studentsQueue())
                .to(studentsExchange())
                .with(studentsKeyName);
    }

    @Bean
    public Queue registrationsQueue(){
        return new Queue(registrationsQueueName);
    }

    @Bean
    public TopicExchange registrationsExchange(){
        return new TopicExchange(registrationsExchangeName);
    }

    @Bean
    public Binding registrationBinding(){
        return BindingBuilder
                .bind(registrationsQueue())
                .to(registrationsExchange())
                .with(registrationsKeyName);
    }

    @Bean
    public MessageConverter jsonConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }
}
