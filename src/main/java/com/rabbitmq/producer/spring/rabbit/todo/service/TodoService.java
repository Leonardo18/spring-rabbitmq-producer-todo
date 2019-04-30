package com.rabbitmq.producer.spring.rabbit.todo.service;

import com.rabbitmq.producer.spring.rabbit.todo.api.dto.TodoDto;
import com.rabbitmq.producer.spring.rabbit.todo.config.RabbitConfig;
import com.rabbitmq.producer.spring.rabbit.todo.model.TodoModel;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private ModelMapper modelMapper;
    private RabbitTemplate rabbitTemplate;
    private RabbitConfig rabbitConfig;

    @Autowired
    public TodoService(RabbitTemplate rabbitTemplate, RabbitConfig rabbitConfig, ModelMapper modelMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
        this.modelMapper = modelMapper;
    }

    public TodoDto sendTodoToRabbitQueue(TodoModel todoModel) {
        publishTodoEvent(todoModel);
        return convertTodoDtoToTodoModel(todoModel);
    }

    private void publishTodoEvent(TodoModel todoModel) {
        rabbitTemplate.convertAndSend(rabbitConfig.getRabbitExchange(), "#", todoModel.toString());
    }

    private TodoDto convertTodoDtoToTodoModel(TodoModel todoModel){ return modelMapper.map(todoModel, TodoDto.class); }
}
