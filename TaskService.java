package com.geekbrains.traning.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    @Qualifier(value = "taskRepositoryHibernate")
    // Почему-то не сработало с указанием бина как интерфейса taskRepository, хотя других классов реализующих интерфейс сейчас нет
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskService() {
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
