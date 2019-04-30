package com.rabbitmq.producer.spring.rabbit.todo.bean;

import com.rabbitmq.producer.spring.rabbit.todo.config.RabbitConfig;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitBean {

    private RabbitConfig rabbitConfig;
    private static final String DEFAULT_ROUTING_KEY = "#";

    @Autowired
    public RabbitBean(RabbitConfig rabbitConfig) {
        this.rabbitConfig = rabbitConfig;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbitConfig.getRabbitUrl());
        connectionFactory.setUsername(rabbitConfig.getRabbitUser());
        connectionFactory.setPassword(rabbitConfig.getRabbitPass());

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        DirectExchange directExchange = new DirectExchange(rabbitConfig.getRabbitExchange());

        Queue queue = new Queue(rabbitConfig.getRabbitQueue());

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(directExchange);

        rabbitAdmin.declareBinding(
                BindingBuilder
                        .bind(queue)
                        .to(directExchange)
                        .with(DEFAULT_ROUTING_KEY));

        return connectionFactory;
    }
}
