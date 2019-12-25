package com.geekbrains.server.services;

import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.*;
import com.geekbrains.server.mappers.TaskMapper;
import com.geekbrains.server.repositories.TaskRepository;
import com.geekbrains.server.repositories.specifications.TaskSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final int taskPerPage = 10;
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

        Pageable pageable =  PageRequest.of(pageNumber.intValue() - 1, taskPerPage);
        return taskRep.findAll(spec, pageable);
    }

    public List<TaskDto> getAllTasksDto (Long pageNumber, String title, User owner, User executer, Status status) {
        Page<Task> page = getAllTasks(pageNumber, title, owner, executer, status);
        List<Task> tasks = page.get().collect(Collectors.toList());
        return TaskMapper.MAPPER.fromTaskList(tasks);
    }

    public Task getTask(Long id) {
        return taskRep.findById(id).get();
    }
}
