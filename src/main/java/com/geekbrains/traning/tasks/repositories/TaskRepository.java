package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Task;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    void updateTask(Task task);
    void delTask(Long id);
    List<Task> getAllTasks();
}
