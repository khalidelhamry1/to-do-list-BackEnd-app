package com.example.demo.dto;

import com.example.demo.entities.Status;

import java.time.LocalDateTime;

public record TaskDto (Long taskId,
                       String title,
                       String description,
                       LocalDateTime startDate,
                       LocalDateTime finishDate,
                       Status status
){

}
