package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.entities.Task;
import com.geekbrains.traning.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRep;

    @Autowired
    public void setTaskRep(TaskRepository taskRep) {
        this.taskRep = taskRep;
    }

    public void addTask(Task task) {
        taskRep.addTask(task);
    }

    public void updateTask(Task task) {
        taskRep.updateTask(task);
    }

    public void delTask(Long id) {
        taskRep.delTask(id);
    }

    public List<Task> getAllTasks (String title, Status status) {
        return taskRep.getAllTasks(title, status);
    }

    public Task getTask(Long id) {
        return taskRep.getTask(id);
    }

    public void printTasks() {
        List<Task> tasks = taskRep.getAllTasks(null, null);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }
}
