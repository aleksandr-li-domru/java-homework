package com.geekbrains.traning.tasks;

import java.util.List;

public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    public void delTask(Long id) {
        repository.delTask(id);
    }

    public List<Task> getAllTasks () {
        return repository.getAllTasks();
    }

    public void printTasks() {
        List<Task> tasks = repository.getAllTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }

    public User getUserByName(String name) {
        return repository.getUserByName(name);
    }

    public Status getStatusByName(String name) {
        return repository.getStatusByName(name);
    }
}
