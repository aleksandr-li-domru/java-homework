package com.geekbrains.traning.tasks;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    void updateTask(Task task);
    void delTask(Long id);
    List<Task> getAllTasks();
    User getUserByName(String name);
    Status getStatusByName(String name);
}
