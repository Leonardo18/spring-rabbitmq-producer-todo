package com.rabbitmq.producer.spring.rabbit.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rabbitmq.producer.spring.rabbit.todo")
public class SpringRabbitTodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitTodoApplication.class, args);
	}
}
