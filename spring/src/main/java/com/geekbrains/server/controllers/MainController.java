package com.geekbrains.server.controllers;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
import com.geekbrains.server.entities.*;
import com.geekbrains.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    private TaskService taskService;
    private StatusService statusService;
    private UserService userService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/tasks")
    public List<TaskDto> showAllTasks(@RequestParam(defaultValue = "1") Long pageNumber,
                               @RequestParam(value = "title", required = false) String title,
                               @RequestParam(value = "owner_id", required = false) Long ownerId,
                               @RequestParam(value = "exec_id", required = false) Long executerId,
                               @RequestParam(value = "status_id", required = false) Long statusId) {
        User owner = null;
        User executer = null;
        Status status = null;
        if (ownerId != null) {
            owner = userService.getUserById(ownerId);
        }
        if (executerId != null) {
            executer = userService.getUserById(executerId);
        }
        if (statusId != null) {
            status = statusService.getStatusById(statusId);
        }
        return taskService.getAllTasksDto(pageNumber, title, owner, executer, status);
    }

    @GetMapping("/tasks/remove/{id}")
    public ResponseEntity<String> removeItems(@PathVariable Long id) {
        taskService.delTask(id);
        return new ResponseEntity<String>("Successfully removed", HttpStatus.OK);
    }

    @GetMapping("/tasks/status")
    public List<StatusDto> getStatuses() {
        return statusService.getStatusesDto();
    }

    @GetMapping("/tasks/users")
    public List<UserDto> getUsers() {
        return userService.getUsersDto();
    }
}