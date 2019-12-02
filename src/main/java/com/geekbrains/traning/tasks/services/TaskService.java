package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.entities.Task;
import com.geekbrains.traning.tasks.entities.User;
import com.geekbrains.traning.tasks.repositories.DictRepository;
import com.geekbrains.traning.tasks.repositories.TaskRepository;
import com.geekbrains.traning.tasks.repositories.UserRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRep;
    private DictRepository dictRep;

    private UserRpository userRep;

    @Autowired
    public void setTaskRep(TaskRepository taskRep) {
        this.taskRep = taskRep;
    }

    @Autowired
    public void setDictRep(DictRepository dictRep) {
        this.dictRep = dictRep;
    }

    @Autowired
    public void setUserRep(UserRpository userRep) {
        this.userRep = userRep;
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

    public List<Task> getAllTasks () {
        return taskRep.getAllTasks();
    }

    public void printTasks() {
        List<Task> tasks = taskRep.getAllTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }

    public User getUserById(Long id) {
        return userRep.getUserById(id);
    }

    public User getUserByName(String name) {
        return userRep.getUserByName(name);
    }

    public List<User> getUsers() {
        return userRep.getUsers();
    }

    public Status getStatusById(Long id) {
        return dictRep.getStatusById(id);
    }

    public Status getStatusByName(String name) {
        return dictRep.getStatusByName(name);
    }

    public List<Status> getStatuses() {
        return dictRep.getStatuses();
    }


}
