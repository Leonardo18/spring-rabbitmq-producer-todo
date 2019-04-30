package com.rabbitmq.producer.spring.rabbit.todo.api.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TodoDto {

    @NotNull(message = "Field name can't be empty")
    private String name;
    @NotNull(message = "Field description can't be empty")
    private String description;
    @NotNull(message = "Field priority can't be empty")
    @Min(value = 1, message = "The value to priority can't be minor then 1")
    @Max(value = 5, message = "The value to priority can't be major then 5")
    private Integer priority;
    @NotNull(message = "Field author can't be empty")
    private String author;

    public TodoDto() { }

    public TodoDto(String name, String description, Integer priority, String author) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() { return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE); }
}
