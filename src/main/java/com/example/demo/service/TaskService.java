package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.dto.TaskDtoMapper;
import com.example.demo.entities.Task;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskDtoMapper taskDtoMapper;

    public TaskService(TaskRepository taskRepository,
                       TaskDtoMapper taskDtoMapper) {
        this.taskRepository = taskRepository;
        this.taskDtoMapper = taskDtoMapper;
    }

    public List<TaskDto> getTask() {
        return taskRepository.findAll().stream()
                .map(taskDtoMapper)
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long taskId) {
        return taskRepository.findById(taskId).map(taskDtoMapper)
                .orElseThrow(()->new ResourceNotFoundException("task not found"));
    }

    public void addTask(Task task) {
         taskRepository.save(task);
    }
    public void deleteTask(Long taskId) {
        Task task1= taskRepository.findById(taskId)
                .orElseThrow(()->new ResourceNotFoundException("task not found"));;
        taskRepository.delete(task1);
    }

    public void updateTask(Long taskId, Task task) {
       Task task1= taskRepository.findById(taskId)
               .orElseThrow(()->new ResourceNotFoundException("task not found"));
        task1.setDescription(task.getDescription());
        task1.setTitle(task.getTitle());
        task1.setStartDate(task.getStartDate());
        task1.setFinishDate(task.getFinishDate());
        task1.setStatus(task.getStatus());
        taskRepository.save(task1);


    }

}
