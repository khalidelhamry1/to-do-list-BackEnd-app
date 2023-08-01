package com.example.demo.repository;

import com.example.demo.entities.Status;
import com.example.demo.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private  TaskRepository taskRepository;
    @Test
    public void saveTask(){
        Task task=Task.builder()
                .title("Task 1")
                .description("Description 1")
                .startDate(LocalDateTime.parse("2022-12-12T23:59:59"))
                .finishDate(LocalDateTime.parse("2022-12-12T23:59:59"))
                //.finishDate(LocalDateTime.of(2022, 12, 12, 23, 59, 59))
                .status(Status.completed)
                .build();

        taskRepository.save(task);
    }



}