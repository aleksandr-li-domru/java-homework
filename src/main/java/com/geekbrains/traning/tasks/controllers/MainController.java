package com.geekbrains.traning.tasks.controllers;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.entities.Task;
import com.geekbrains.traning.tasks.entities.User;
import com.geekbrains.traning.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String showAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);

        List<User> users = taskService.getUsers();
        List<Status> statuses = taskService.getStatuses();
        model.addAttribute("users", users);
        model.addAttribute("statuses", statuses);

        return "add";
    }

    // Не работает
    @RequestMapping(value = "/add_task", method = RequestMethod.POST)
    public String addNewTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/add_task_simple", method = RequestMethod.POST)
    public String addNewTaskSimple(String title, String description, Long owner, Long executer, Long status) {
        User own = taskService.getUserById(owner);
        User exec = taskService.getUserById(executer);
        Status stat = taskService.getStatusById(status);
        Task task = new Task(null, title, own, exec, description, stat);
        taskService.addTask(task);
        return "redirect:/tasks";
    }
}
