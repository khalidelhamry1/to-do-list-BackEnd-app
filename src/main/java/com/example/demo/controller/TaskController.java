package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.entities.Member;
import com.example.demo.entities.Task;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public List<TaskDto> getTask(){
        return taskService.getTask();
    }
    @GetMapping("{taskId}")
    public TaskDto getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }
    @PostMapping
    public void addTask(@RequestBody Task task){
         taskService.addTask(task);
    }
    @DeleteMapping("{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }
    @PutMapping("{taskId}")
    public void updateTask(@PathVariable("taskId") Long taskId,@RequestBody Task task) {
        taskService.updateTask(taskId,task);
    }

}
