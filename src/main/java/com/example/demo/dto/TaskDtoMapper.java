package com.example.demo.dto;

import com.example.demo.entities.Task;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TaskDtoMapper implements Function<Task,TaskDto> {
    @Override
    public TaskDto apply(Task task) {
        return new TaskDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getFinishDate(),
                task.getStatus()
        );
    }
}
