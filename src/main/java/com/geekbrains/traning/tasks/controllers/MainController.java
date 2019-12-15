package com.geekbrains.traning.tasks.controllers;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.entities.Task;
import com.geekbrains.traning.tasks.entities.User;
import com.geekbrains.traning.tasks.services.StatusService;
import com.geekbrains.traning.tasks.services.TaskService;
import com.geekbrains.traning.tasks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String showAllTasks(@RequestParam(defaultValue = "1") Long pageNumber,
                               @RequestParam(value = "title", required = false) String title,
                               @RequestParam(value = "owner_id", required = false) Long ownerId,
                               @RequestParam(value = "exec_id", required = false) Long executerId,
                               @RequestParam(value = "status_id", required = false) Long statusId,
                               Model model) {
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
        Page<Task> tasks = taskService.getAllTasks(pageNumber, title, owner, executer, status);
        model.addAttribute("tasks", tasks);
        model.addAttribute("statuses", statusService.getStatuses());
        model.addAttribute("users", userService.getUsers());
        return "tasks";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);

        List<User> users = userService.getUsers();
        List<Status> statuses = statusService.getStatuses();
        model.addAttribute("users", users);
        model.addAttribute("statuses", statuses);

        return "add";
    }

    @RequestMapping(value = "/add_task_simple", method = RequestMethod.POST)
    public String addNewTaskSimple(String title, String description, Long owner, Long executer, Long status) {
        User own = userService.getUserById(owner);
        User exec = userService.getUserById(executer);
        Status stat = statusService.getStatusById(status);
        Task task = new Task(null, title, own, exec, description, stat);
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewTask(@PathVariable Long id, Model model) {
        model.addAttribute("task",  taskService.getTask(id));
        return "view";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delTask(@PathVariable Long id) {
        taskService.delTask(id);
        return "redirect:/tasks";
    }
}
