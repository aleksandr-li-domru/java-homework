package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.*;
import com.geekbrains.traning.tasks.repositories.TaskRepository;
import com.geekbrains.traning.tasks.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final int productsPerPage = 10;
    private TaskRepository taskRep;

    @Autowired
    public void setTaskRep(TaskRepository taskRep) {
        this.taskRep = taskRep;
    }

    public void addTask(Task task) {
        taskRep.save(task);
    }

    public void delTask(Long id) {
        taskRep.deleteById(id);
    }

    public Page<Task> getAllTasks (Long pageNumber, String title, User owner, User executer, Status status) {
        Specification<Task> spec = Specification.where(null);
        if (title != null && title.length() > 0) {
            spec = spec.and(TaskSpecifications.titleContains(title));
        }
        if (owner != null) {
            spec = spec.and(TaskSpecifications.ownerEquals(owner));
        }
        if (executer != null) {
            spec = spec.and(TaskSpecifications.executerEquals(executer));
        }
        if (status != null) {
            spec = spec.and(TaskSpecifications.statusEquals(status));
        }

        Pageable pageable =  PageRequest.of(pageNumber.intValue() - 1, productsPerPage);
        return taskRep.findAll(spec, pageable);
    }

    public Task getTask(Long id) {
        return taskRep.findById(id).get();
    }
}
